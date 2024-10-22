package study.supercodingboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;
    @Size(min = 2, message = "내용은 2글자 이상이어야 합니다!!")
    private String content;
    @NotBlank(message = "작성자를 입력해 주세요.")
    private String author;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime createdAt;
}
