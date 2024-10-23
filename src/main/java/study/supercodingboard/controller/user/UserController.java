package study.supercodingboard.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import study.supercodingboard.dto.user.JoinDTO;
import study.supercodingboard.dto.user.LogoutDTO;
import study.supercodingboard.dto.user.ResponseDTO;
import study.supercodingboard.jwt.JWTUtil;
import study.supercodingboard.service.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody JoinDTO joinDTO) {
        ResponseDTO response = userService.joinProcess(joinDTO);

        if (response.getMessage().contains("이메일 중복")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // Return 400 Bad Request
        }

        return ResponseEntity.ok(response);  // Return 201 Created for success
    }
    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestBody LogoutDTO logoutDTO, HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);


        if (token != null && !token.isEmpty()) {

            userService.logoutProcess(logoutDTO.getEmail(), token);
        }


        ResponseDTO response = new ResponseDTO("로그아웃되었습니다.");
        return ResponseEntity.ok(response);
    }





}
