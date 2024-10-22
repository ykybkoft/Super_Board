package study.supercodingboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercodingboard.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
