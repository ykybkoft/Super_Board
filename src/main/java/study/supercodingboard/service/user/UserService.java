package study.supercodingboard.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.supercodingboard.dto.user.JoinDTO;
import study.supercodingboard.dto.user.ResponseDTO;
import study.supercodingboard.entity.user.UserEntity;
import study.supercodingboard.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenStoreService tokenStoreService;

    public ResponseDTO joinProcess(JoinDTO joinDTO){
        String username = joinDTO.getEmail();
        String password = joinDTO.getPassword();
        ResponseDTO response = new ResponseDTO();

        Boolean isExist = userRepository.existsByUsername(username);
        if (isExist){

            response.setMessage("회원가입에 실패하셨습니다.(이메일 중복)");
            return response;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");

        userRepository.save(data);
        response.setMessage("회원가입이 완료되었습니다.");

        return response;
    }

    public void logoutProcess(String email, String token) {
        // Add the token to the blacklist to invalidate it
        tokenStoreService.addTokenToBlacklist(token);
        System.out.println("Logging out user: " + email + " with token: " + token);
    }



}
