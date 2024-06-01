package org.sopt.spring.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.common.auth.UserAuthentication;
import org.sopt.spring.common.auth.redis.domain.Token;
import org.sopt.spring.common.auth.redis.repository.RedisTokenRepository;
import org.sopt.spring.common.dto.ErrorMessage;
import org.sopt.spring.common.dto.UserJoinResponse;
import org.sopt.spring.common.jwt.JwtTokenProvider;
import org.sopt.spring.domain.Member;
import org.sopt.spring.exception.NotFoundException;
import org.sopt.spring.exception.UnauthorizedException;
import org.sopt.spring.repository.MemberRepository;
import org.sopt.spring.service.dto.MemberCreateDto;
import org.sopt.spring.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );

        redisTokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }
    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }

    @Transactional
    public UserJoinResponse refreshToken(Long memberId) {
        if(!redisTokenRepository.existsById(memberId.toString())){
            throw new UnauthorizedException(ErrorMessage.INVALID_REFRESH_TOKEN);
        }
        findById(memberId);

        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        redisTokenRepository.save(Token.of(memberId, refreshToken));
        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }
}
