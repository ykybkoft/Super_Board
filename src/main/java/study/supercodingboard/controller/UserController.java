package study.supercodingboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseDTO signUp(@RequestBody JoinDTO joinDTO){
        return userService.joinProcess(joinDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestBody LogoutDTO logoutDTO, HttpServletRequest request) {
        String token = jwtUtil.extractTokenFromRequest(request);

        // Validate the token if necessary
        if (token != null && !token.isEmpty()) {
            // Pass the email and token to the logout process
            userService.logoutProcess(logoutDTO.getEmail(), token);
        }

        // Return a response confirming logout
        ResponseDTO response = new ResponseDTO("로그아웃되었습니다.");
        return ResponseEntity.ok(response);
    }



}
