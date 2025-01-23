package com.kmu.anki.backend.global.config;

import com.kmu.anki.backend.domain.auth.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Profile({"test", "prod"}) // dev, test 프로파일에서만 적용
    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    @Profile("dev") // dev, test 프로파일에서만 적용
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 0. CSRF 비활성화 (필요 시)
                .csrf(csrf -> csrf.disable())
                // 1. 인증/인가 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login.do").permitAll() // 누구나 접근 가능
                        .anyRequest().authenticated()                                   // 나머지는 인증 필요
                )

                // 2. Form 기반 로그인 설정
                .formLogin(form -> form
                        .loginPage("/api/auth/login.do") // 커스텀 로그인 페이지
                        .permitAll()
                )

                // 3. OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/api/auth/login.do") // 로그인 페이지를 커스텀 경로로 설정
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // OAuth2 인증 성공 후 이동 경로
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // Custom OAuth2 User Service 등록
                        )
                )
                // 4. 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")                     // 로그아웃 처리 URL
                        .logoutSuccessUrl("/")                    // 로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true)              // 세션 무효화
                        .deleteCookies("JSESSIONID")              // 쿠키 삭제
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화용
    }

}
