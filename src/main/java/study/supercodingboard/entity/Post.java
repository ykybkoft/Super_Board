package study.supercodingboard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import study.supercodingboard.dto.PostDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "posts")
@Slf4j
public class Post extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;

    public static Post savePost(PostDto postDto) {
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .author(postDto.getAuthor())
                .build();
    }

    public PostDto getPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public Post updatePost(PostDto postDto) {

        if(postDto.getTitle() != null){
            title = postDto.getTitle();
        }
        if(postDto.getContent() != null){
            content = postDto.getContent();
        }
        return this;
    }
}
