package study.supercodingboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercodingboard.entity.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String email);

    UserEntity findByUsername(String email);
}
