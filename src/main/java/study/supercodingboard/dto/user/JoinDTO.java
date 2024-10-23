package study.supercodingboard.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
public class JoinDTO {
    private String email;
    private String password;
}
