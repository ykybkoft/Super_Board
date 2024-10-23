package study.supercodingboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.supercodingboard.dto.PostDto;
import study.supercodingboard.dto.PostMessage;
import study.supercodingboard.entity.Post;
import study.supercodingboard.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public PostMessage savePost(PostDto postDto) {
        Post savedPost = postRepository.save(Post.savePost(postDto));

        if(savedPost.getId() > 0){
            return PostMessage.builder()
                    .message("게시물이 성공적으로 작성되었습니다.")
                    .build();
        }else{
            throw new IllegalStateException("게시물 작성이 실패하였습니다.");
        }
    }

    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(post -> post.getPostDto(post))
                .toList();
    }

    public PostMessage updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalStateException::new);

        Post updatePost = postRepository.save(post.updatePost(postDto));

        if(updatePost.getId() > 0){
            return PostMessage.builder()
                    .message("게시글이 설공적으로 수정 되었습니다.")
                    .build();
        }else{
            throw new IllegalStateException("게시글 수정이 실패 했습니다.");
        }
    }

    public List<PostDto> findByAuthor(String Author) {
        List<PostDto> posts = postRepository.findByAuthor(Author).stream().map(post -> post.getPostDto(post)).toList();
        log.info("작성일 : {}", posts.get(0).getCreatedAt().toString());
        return posts;
    }

    public PostDto findById(Long postId) {
        return postRepository.findById(postId)
                .map(post -> post.getPostDto(post))
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다.: " + postId));
    }

    public PostMessage deletePost(Long postId) {
        postRepository.deleteById(postId);
        return PostMessage.builder().message("게시글이 삭제 되었습니다.").build();
    }
}
