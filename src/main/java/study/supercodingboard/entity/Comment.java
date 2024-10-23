package study.supercodingboard.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseTime { // BaseTime 클래스 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String author;

    @JsonProperty("post_id")
    private Long postId; // 게시물 ID

    public Comment() {}

    public Comment(Long id, String content, String author, Long postId) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.postId = postId;
    }
}
