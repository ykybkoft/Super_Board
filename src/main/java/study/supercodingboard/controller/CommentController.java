package study.supercodingboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercodingboard.entity.Comment;
import study.supercodingboard.dto.CommentResponse;
import study.supercodingboard.service.CommentService;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;// 생성자에서 CommentService 초기화
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId)
    {
        return commentService.findByPostId(postId); // 특정 게시글의 댓글 목록 반환
    }

    // 댓글 생성
    @PostMapping("/{commentId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long commentId, @RequestBody Comment comment)
    {
        commentService.createComment(comment, commentId);
        CommentResponse response = new CommentResponse("댓글이 성공적으로 작성되었습니다.");
        return ResponseEntity.ok(response);
    }


    // 댓글 수정 (선택적)
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentService.updateComment(commentId, comment.getContent());
        CommentResponse response = new CommentResponse("댓글이 성공적으로 수정되었습니다.");
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        CommentResponse response = new CommentResponse("댓글이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }
}
