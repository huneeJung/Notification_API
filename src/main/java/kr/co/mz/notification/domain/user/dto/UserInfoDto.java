package kr.co.mz.notification.domain.user.dto;

import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserInfoDto {

    private String name;
    private String email;
    private UserEntity.RoleType role;


    public static UserInfoDto from(UserEntity userEntity) {
        return UserInfoDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }
}
