package org.sopt.spring.common.dto;
//왜 안될까
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),

    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 블로그가 없습니다."),

    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),

    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(), "REFRESH 토큰이 만료되었습니다."),
    ;


    private final int status;
    private final String message;
}
