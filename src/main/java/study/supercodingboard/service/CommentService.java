package study.supercodingboard.service;

import study.supercodingboard.model.Comment;
import study.supercodingboard.repository.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;


    public CommentService() {
        this.commentRepository = new CommentRepository(); // 생성자에서 CommentRepository 초기화
    }

    public void createComment(Comment comment) {
        commentRepository.save(comment); // 댓글을 저장하는 메서드 호출
    }

    public List<Comment> findAll()
    {
        return commentRepository.findAll(); // 데이터베이스에서 모든 댓글을 조회하여 반환
    }


    // 댓글 수정
    public void updateComment(Long commentId, String content) {
        commentRepository.updateContent(commentId, content); // 댓글 내용을 수정하는 메서드 호출
    }

    // 댓글 삭제
    public void deleteComment(Long commentId)
    {
        commentRepository.deleteContent(commentId);

    }
}
