package com.kmu.anki.backend.domain.auth.controller;

import com.kmu.anki.backend.global.AbstractControllerTest;
import com.kmu.anki.backend.global.auth.WithMockCustomOAuth2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
class AuthControllerTest extends AbstractControllerTest {

    @Disabled
    @WithMockCustomOAuth2
    @DisplayName("로그아웃 API")
    @Test
    void logout() throws Exception {
        // 🔹 Mock 세션 생성
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", "mockContext");

        mockMvc.perform(get("/api/auth/logout.do") // 🔹 로그아웃 API 호출
                        .session(session)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // 🔹 CSRF 설정 추가
                .andExpect(status().isOk())  // HTTP 200 응답 확인
                .andExpect(jsonPath("$.message").value("로그아웃 성공"))  // 응답 메시지 확인
                .andDo(MockMvcResultHandlers.print());  // 테스트 결과 출력

        // 🔹 세션이 무효화되었는지 확인
        assertThat(session.isInvalid()).isTrue();
    }
}