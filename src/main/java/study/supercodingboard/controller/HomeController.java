package study.supercodingboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.supercodingboard.dto.PostDto;
import study.supercodingboard.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String author, Model model) {
        List<PostDto> posts;

        if (author != null && !author.isEmpty()) {
            posts = postService.findByAuthor(author);
        } else {
            posts = postService.findAll();
        }

        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm(Model model) {
        return "boardWriteForm";
    }

    @GetMapping("/postUpdate/{post_id}")
    public String postUpdate(@PathVariable Long post_id, Model model) {
        PostDto postDto = postService.findById(post_id);
        model.addAttribute("post", postDto);
        return "postUpdateForm";
    }


    /**
     * id를 통한 특정 게시물 상세페이지 이동
     */
    @GetMapping("/posts/{post_id}")
    public String post(@PathVariable Long post_id, Model model) {
        PostDto postDto = postService.findById(post_id);
        model.addAttribute("post", postDto);
        return "postDetail";
    }
}
