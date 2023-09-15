package kr.co.mz.notification.domain.user.service;

import kr.co.mz.notification.common.exception.custom.CustomApiException;
import kr.co.mz.notification.common.exception.dto.ErrorCode;
import kr.co.mz.notification.common.util.encoder.PasswordEncoderUtil;
import kr.co.mz.notification.domain.user.dto.SignUpDto;
import kr.co.mz.notification.domain.user.dto.UserInfoDto;
import kr.co.mz.notification.domain.user.entity.UserEntity;
import kr.co.mz.notification.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Transactional
    public void registerUser(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new CustomApiException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        UserEntity userEntity = UserEntity.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoderUtil.encodePassword(signUpDto.getPassword()))
                .role(UserEntity.RoleType.ADMIN)
                .build();

        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public UserInfoDto getUser(Integer userId) {
        log.info("userService getUser run");
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.USER_NOT_FOUND));
        return UserInfoDto.from(userEntity);
    }
}
