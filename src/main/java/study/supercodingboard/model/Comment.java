package study.supercodingboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Comment {
    private Long id; // ID
    private String content; // 댓글 내용
    private String author; // 작성자
    @JsonProperty("post_id")
    private Long postId; // 게시물 ID
    private String createdAt;

    public Comment()
    {
    }

    public Comment(Long id, String content, String author, Long postId, String createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.postId = postId;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
