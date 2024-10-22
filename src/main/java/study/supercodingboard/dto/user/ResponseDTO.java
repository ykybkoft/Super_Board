package study.supercodingboard.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String message;

    public ResponseDTO(){

    }
    public ResponseDTO(String s) {
        message=s;
    }
}
