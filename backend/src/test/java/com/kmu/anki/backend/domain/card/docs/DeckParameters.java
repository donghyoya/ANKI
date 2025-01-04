package com.kmu.anki.backend.domain.card.docs;

import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class DeckParameters {
    public static ParameterDescriptor queryType = parameterWithName("queryType").description("의미에 따른 분류인가 (level) / 난이도에 따른 분류인가 (meaning)");

    public static ParameterDescriptor query = parameterWithName("query").description("검색어 (easy-normal-hard 등)");
}
