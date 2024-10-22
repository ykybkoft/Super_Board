package study.supercodingboard.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutDTO {
    private String email;
    private String token;

}
