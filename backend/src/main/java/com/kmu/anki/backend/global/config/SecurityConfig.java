package com.kmu.anki.backend.global.config;

import com.kmu.anki.backend.security.auth.filter.JwtAuthenticationFilter;
import com.kmu.anki.backend.security.auth.oauth2.CustomOidcService;
import com.kmu.anki.backend.security.auth.oauth2.TokenProvideSuccessHandler;
import com.kmu.anki.backend.security.auth.token.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    @Value("${auth.jwt.secret:defaultsecretkeydefaultsecretkeydefaultsecretkey==}")
    private String secretKey; // properties에서 읽어옴

    private final CustomOidcService oidcService;

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
                // 1. 인가 설정
                .oauth2Login(
                        config->config.userInfoEndpoint(
                                userInfoEndpointConfig -> userInfoEndpointConfig.oidcUserService(oidcService)
                        ).successHandler(authenticationSuccessHandler())
                                .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig.baseUri("/api/auth/oauth2/google"))
                )
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
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/get-test-token").permitAll()
                        .anyRequest().authenticated()                                   // 나머지는 인증 필요
                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
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
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://hada.zapto.org:3000",
                "http://local.hada.zapto.org:3000"
        ));
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

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new TokenProvideSuccessHandler(jwtTokenService());
    }

}
