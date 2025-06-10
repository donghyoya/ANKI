package com.kmu.anki.backend.domain.card.docs;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;

public class AuthHeaders {
    public static HeaderDescriptorWithType requestAuthHeader = new HeaderDescriptorWithType("Authorization").description("액세스 토큰 (Bearer)");
}
