package study.supercodingboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.supercodingboard.entity.Comment;
import study.supercodingboard.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository; // 생성자에서 CommentRepository 초기화
    }

    public void createComment(Comment comment) {
        commentRepository.save(comment); // 댓글을 저장하는 메서드 호출
    }

    public List<Comment> findAll() {
        return commentRepository.findAll(); // 데이터베이스에서 모든 댓글을 조회하여 반환
    }

    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException());
        comment.setContent(content); // 댓글 내용 수정
        commentRepository.save(comment); // 변경 사항 저장
    }

    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException(); // 존재하지 않는 경우 예외 처리
        }
        commentRepository.deleteById(commentId); // 댓글 삭제
    }
}
