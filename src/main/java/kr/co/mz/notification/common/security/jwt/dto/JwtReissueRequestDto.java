package kr.co.mz.notification.common.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtReissueRequestDto {

    private String accessToken;
    private String refreshToken;

}
