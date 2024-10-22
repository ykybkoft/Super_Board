package study.supercodingboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// 댓글 생성 후 응답을 위한 DTO 클래스
public class CommentResponse
{
    // DELETE 요청으로 삭제시 빈 응답을 반환하는데 아래의 어노테이션이 없으면 json 형식으로 삭제완료 메시지를 반환할때 충돌이 생김
    @JsonProperty("message") // 직렬화에 사용될 필드 명시
    private String message; // 응답 메시지

    public CommentResponse(String message) {
        this.message = message;
    }


}
