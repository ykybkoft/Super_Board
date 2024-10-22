package study.supercodingboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercodingboard.dto.CommentResponse; // 추가된 DTO
import study.supercodingboard.model.Comment;
import study.supercodingboard.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController
{
    private final CommentService commentService;

    public CommentController()
    {
        this.commentService = new CommentService(); // 생성자에서 CommentService 초기화
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody Comment comment) // 스프링이 json을 Comment 객체로 자동변환 해줌
    {
        commentService.createComment(comment); // 댓글 생성 메서드 호출
        CommentResponse response = new CommentResponse("댓글이 성공적으로 작성되었습니다.");
        return ResponseEntity.ok(response); // JSON 형식
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAll(); // 댓글 리스트를 가져옴
        return ResponseEntity.ok(comments); // 댓글 리스트를 JSON으로 반환
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentService.updateComment(commentId, comment.getContent()); // 댓글 수정 메서드 호출
        CommentResponse response = new CommentResponse("댓글이 성공적으로 수정되었습니다.");
        return ResponseEntity.ok(response); // JSON 형식으로 응답 반환
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId)
    {
        commentService.deleteComment(commentId); // 댓글 삭제 메서드 호출
        CommentResponse response = new CommentResponse("댓글이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response); // JSON 형식으로 응답 반환
    }
}
