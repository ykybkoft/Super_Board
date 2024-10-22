package study.supercodingboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.supercodingboard.dto.PostDto;
import study.supercodingboard.dto.PostMessage;
import study.supercodingboard.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

    private final PostService postService;

    /**
     * 게시글 작성 api
     */
    @PostMapping("/posts")
    private ResponseEntity<PostMessage> postSave(@RequestBody @Valid PostDto postDto) {
        return ResponseEntity.ok(postService.savePost(postDto));
    }

    /**
     * 게시물 전체 조회 api
     */
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    /**
     * id를 통한 특정 게시물 조회 api
     */
    @PutMapping("/posts/{post_id}")
    public ResponseEntity<PostMessage> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "post_id") Long postId){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }
}
