package study.supercodingboard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.supercodingboard.jwt.JWTFilter;
import study.supercodingboard.jwt.JWTUtil;
import study.supercodingboard.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //csrf 끄기
        http.csrf(auth -> auth.disable());

        //formLogin 방식 끄기
        http.formLogin(auth -> auth.disable());

        //http basic 인증 방식 끄기
        http.httpBasic(auth -> auth.disable());



        //로그인 안해도 허용할 페이지 설정
        http.authorizeHttpRequests((auth) -> auth
                .anyRequest().permitAll());

//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/index", "/api/signup", "/api/login").permitAll()  // 허용할 경로들 설정
//                .anyRequest().authenticated()  // 그 외 모든 경로는 인증 필요
//        );


        //세셩 설정 , Stateless
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil);
        loginFilter.setFilterProcessesUrl("/api/login");

        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);



        http.exceptionHandling(handler -> handler.accessDeniedPage("/"));


        return http.build();
    }


}
