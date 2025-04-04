package com.kmu.anki.backend.global.config;

import com.kmu.anki.backend.domain.auth.service.CustomOAuth2UserService;
import com.kmu.anki.backend.domain.auth.service.CustomUserService;
import com.kmu.anki.backend.security.auth.filter.JwtAuthenticationFilter;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Value("${jwt.secret:defaultsecretkeydefaultsecretkeydefaultsecretkey==}")
    private String secretKey; // properties에서 읽어옴

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomUserService customUserService;

//    @Bean
//    @Profile({"test"}) // dev, test 프로파일에서만 적용
//    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                )
//                .csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }

    @Bean
    @Profile({"dev", "prod", "test"}) // dev, test 프로파일에서만 적용
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 0. CSRF 비활성화 (필요 시)
                .csrf(csrf -> csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 1. 인증/인가 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/decks").permitAll()
                        .requestMatchers(HttpMethod.GET, "/decks/cards").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cards/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cards/{id}/details").permitAll()
                        .requestMatchers(
                                "/cards/foreign-search",
                                "/cards/korean-search"
                        ).permitAll()
                        .requestMatchers("/api/auth/**", "/css/**").permitAll() // 누구나 접근 가능
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").authenticated()
                        .anyRequest().authenticated()                                   // 나머지는 인증 필요
                )
        ;
        return http.build();
    }

    @Bean
    public JwtTokenService jwtTokenService(){
        return new JwtTokenService(secretKey);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenService());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 cors 옵션 허용
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화용
    }

}
