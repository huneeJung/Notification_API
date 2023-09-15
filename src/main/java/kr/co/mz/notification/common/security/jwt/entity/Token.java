package kr.co.mz.notification.common.security.jwt.entity;

import jakarta.persistence.*;
import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "token")
@ToString(exclude = {"userEntity"})
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "expired_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @PrePersist
    public void createDatetime() {
        this.createdAt = LocalDateTime.now(); // 등록일은 현재 시점으로 설정
        this.expiredAt = LocalDateTime.now().plusMonths(1); // Refresh Token 만료일은 현재로부터 한달뒤로 설정
    }

    @Builder
    public Token(String accessToken, String refreshToken, LocalDateTime createdAt, LocalDateTime expiredAt, UserEntity userEntity) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.userEntity = userEntity;
    }
}
