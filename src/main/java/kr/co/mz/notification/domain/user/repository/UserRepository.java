package kr.co.mz.notification.domain.user.repository;

import kr.co.mz.notification.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNameOrEmail(String name, String email);

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);
}
