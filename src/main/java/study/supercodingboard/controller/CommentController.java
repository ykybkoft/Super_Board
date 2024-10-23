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
        this.commentService = commentService; // 생성자에서 CommentService 초기화
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        CommentResponse response = new CommentResponse("댓글이 성공적으로 작성되었습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentService.updateComment(commentId, comment.getContent());
        CommentResponse response = new CommentResponse("댓글이 성공적으로 수정되었습니다.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        CommentResponse response = new CommentResponse("댓글이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }
}
