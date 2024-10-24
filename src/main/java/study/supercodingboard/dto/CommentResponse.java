package study.supercodingboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// 댓글 생성 후 응답을 위한 DTO 클래스
public class CommentResponse {
    @JsonProperty("message") // 직렬화에 사용될 필드 명시
    private String message; // 응답 메시지

    public CommentResponse(String message) {
        this.message = message;
    }
}
