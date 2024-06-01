package org.sopt.spring.common.dto;

public record UserJoinResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static UserJoinResponse of(
            String accessToken,
            String refreshToken,
            String userId
    ) {
        return new UserJoinResponse(accessToken, refreshToken, userId);
    }
}


