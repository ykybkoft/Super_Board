package study.supercodingboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.supercodingboard.entity.Comment;
import study.supercodingboard.entity.Post;
import study.supercodingboard.repository.CommentRepository;
import study.supercodingboard.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void createComment(Comment comment, Long commentId) {
        Optional<Post> post = postRepository.findById(comment.getPostId());
        String author = post.get().getAuthor(); // 게시물 작성자 설정
        comment.setAuthor(author);
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            e.printStackTrace(); // 오류 로그 출력
            throw new RuntimeException("댓글 저장에 실패했습니다."); // 적절한 예외 처리
        }
    }

    // 댓글 수정
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comment.setContent(content);
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 모든 댓글 조회 (현재 사용되지 않음)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
