package kr.co.mz.notification.domain.user.controller;

import jakarta.validation.Valid;
import kr.co.mz.notification.common.security.dto.PrincipalDetails;
import kr.co.mz.notification.common.security.jwt.JwtUtil;
import kr.co.mz.notification.common.security.jwt.dto.JwtAuthResponseDto;
import kr.co.mz.notification.common.security.jwt.dto.JwtReissueRequestDto;
import kr.co.mz.notification.common.security.jwt.entity.Token;
import kr.co.mz.notification.common.security.jwt.service.JwtService;
import kr.co.mz.notification.common.util.encoder.PasswordEncoderUtil;
import kr.co.mz.notification.domain.user.dto.LoginDto;
import kr.co.mz.notification.domain.user.dto.SignUpDto;
import kr.co.mz.notification.domain.user.dto.UserInfoDto;
import kr.co.mz.notification.domain.user.entity.UserEntity;
import kr.co.mz.notification.domain.user.repository.UserRepository;
import kr.co.mz.notification.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginDto loginDto
    ) {
        UserEntity userEntity = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with username or email : " + loginDto.getEmail()));

        if (!passwordEncoderUtil.checkPassword(loginDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Password is not correct");
        }

        // get accessToken from jwtTokenProvider
        String accessToken = jwtUtil.generateToken(userEntity);
        String refreshToken = UUID.randomUUID().toString();

        // insert accessToken, refreshToken to db
        jwtService.createJwt(accessToken, refreshToken, userEntity);

        return ResponseEntity.ok(new JwtAuthResponseDto(accessToken, refreshToken, userEntity));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody JwtReissueRequestDto requestDto) {

        return jwtService.findByDto(requestDto)
                .map(Token::getUserEntity)
                .map(user -> {
                    // get accessToken from jwtTokenProvider
                    String accessToken = jwtUtil.generateToken(user);
                    String refreshToken = UUID.randomUUID().toString();

                    // insert accessToken, refreshToken to db and delete old refreshToken
                    jwtService.createJwt(accessToken, refreshToken, user);
                    jwtService.deleteByRefreshToken(requestDto.getRefreshToken());

                    return ResponseEntity.ok(new JwtAuthResponseDto(accessToken, refreshToken, user));
                })
                .orElseThrow(() -> new RuntimeException("User not found with username or email"));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignUpDto signUpDto
    ) {
        log.info("signUpDto: {}", signUpDto);
        userService.registerUser(signUpDto);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        log.info("principalDetails: {}", principalDetails);
        UserInfoDto userInfoDto = userService.getUser(principalDetails.getUserEntity().getId());
        return ResponseEntity.ok(userInfoDto);
    }

}
