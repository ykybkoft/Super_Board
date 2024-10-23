package study.supercodingboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
