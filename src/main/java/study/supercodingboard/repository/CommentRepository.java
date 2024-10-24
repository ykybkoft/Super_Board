package study.supercodingboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercodingboard.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // 특정 게시글에 대한 댓글 조회
}
