package kr.co.mz.notification.domain.user.entity;


import jakarta.persistence.*;
import kr.co.mz.notification.common.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    // 단개로 Role 관리
    public enum RoleType {
        USER, ADMIN
    }

    @Builder
    public UserEntity(Long id, String name, String email, String password, RoleType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
