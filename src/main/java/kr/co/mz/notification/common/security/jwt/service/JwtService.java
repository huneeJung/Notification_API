package kr.co.mz.notification.common.security.jwt.service;

import kr.co.mz.notification.common.security.jwt.dto.JwtReissueRequestDto;
import kr.co.mz.notification.common.security.jwt.entity.Token;
import kr.co.mz.notification.common.security.jwt.repository.JwtRepository;
import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtRepository jwtRepository;

    @Transactional
    public void createJwt(String accessToken, String refreshToken, UserEntity userEntity) {
        log.info("JwtService insertJwt run...");
        Token token = Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userEntity(userEntity)
                .build();
        jwtRepository.save(token);
    }

    @Transactional
    public void deleteByRefreshToken(String refreshToken) {
        log.info("JwtService deleteByRefreshToken run...");
        jwtRepository.deleteByRefreshToken(refreshToken);
    }

    @Transactional(readOnly = true)
    public Optional<Token> findByDto(JwtReissueRequestDto requestDto) {

        String accessToken = requestDto.getAccessToken();
        String refreshToken = requestDto.getRefreshToken();
        LocalDateTime now = LocalDateTime.now();

        return jwtRepository.findByAccessTokenAndRefreshTokenAndExpiredAtGreaterThan(accessToken, refreshToken, now);
    }
}
