package study.supercodingboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercodingboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
