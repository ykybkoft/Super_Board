package study.supercodingboard.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseTime { // BaseTime 클래스를 상속받음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 ID

    private String content; // 댓글 내용
    private String author; // 댓글 작성자

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
