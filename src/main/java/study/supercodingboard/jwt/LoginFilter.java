package study.supercodingboard.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;
import study.supercodingboard.dto.user.CustomUserDetails;
import study.supercodingboard.dto.user.JoinDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JoinDTO loginDTO = new JoinDTO();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginDTO = objectMapper.readValue(messageBody, JoinDTO.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = loginDTO.getEmail();
        String password = loginDTO.getPassword();


        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        //System.out.println("로그인 성공");

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60*60*10L);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Authorization", "Bearer " + token);

        String jsonResponse = "{\"message\": \"로그인이 성공적으로 완료되었습니다.\"}";

        try {
            // Write the JSON response
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        //System.out.println("로그인 실패");
        response.setStatus(401);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        // If you're submitting a JSON request, extract the email manually
        return request.getParameter("email");  // Or from JSON body as shown previously
    }

}
