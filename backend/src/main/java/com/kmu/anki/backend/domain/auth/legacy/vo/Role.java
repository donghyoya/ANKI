package com.kmu.anki.backend.domain.auth.legacy.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // private 필드로 생성자 구성
public enum Role {
    GUEST("ROLE_GUEST", "외부자"),
    USER("ROLE_USER", "사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    AUTHENTICAITON("ROLE_AUTHENTICATION", "로그인용 임시토큰"),
    REFRESH("ROLE_REFRESH", "토큰 재발급용");

    private final String key;
    private final String title;
}