package kr.co.mz.notification.common.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kr.co.mz.notification.common.exception.custom.CustomApiException;
import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;           // 설정 파일에서 jwt 토큰에 서명하는데 사용할 secret key를 가져온다.

    @Value("${jwt.token-validity-in-mils}")
    private int jwtExpirationInMs;      // 설정 파일에서 jwt 토큰의 유효기간을 가져온다.

    // generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);
        log.info("jwtExpirationInMs: {}", jwtExpirationInMs);
        log.info("expireDate: " + expireDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(UserEntity userEntity) {
        String username = userEntity.getName();
        Date currentDate = new Date();
//        Date expireDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));    // 7 days
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);    // 7 days
        log.info("jwtExpirationInMs: {}", jwtExpirationInMs);
        log.info("expireDate: " + expireDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new CustomApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}


