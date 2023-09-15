package kr.co.mz.notification.common.security.jwt.dto;

import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtAuthResponseDto {
    private String tokenType = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Integer id;
    private String email;

    public JwtAuthResponseDto(String accessToken, String refreshToken, UserEntity userEntity) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();

    }


}
