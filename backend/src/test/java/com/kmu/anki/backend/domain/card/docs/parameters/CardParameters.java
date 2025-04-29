package com.kmu.anki.backend.domain.card.docs.parameters;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;

import java.util.Arrays;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class CardParameters {
    /* parameters */
    public static ParameterDescriptor cardId = parameterWithName("id").description("카드의 고유번호");
    public static ParameterDescriptor userCardId = parameterWithName("userCardId").description("유저카드의 고유번호");

    public static ParameterDescriptor query = parameterWithName("query").description("검색어");

    public static ParameterDescriptor languageCode = parameterWithName("code")
            .description("검색하고자 하는 언어 코드")
            .attributes(Attributes.key("enumValues").value(Arrays.asList(LanguageCode.values())));

    public static ParameterDescriptor unAuthlanguageCode = parameterWithName("code")
            .description("검색하고자 하는 언어 코드 / 로그인하지 않았을 경우에만 작동함")
            .optional()
            .attributes(Attributes.key("enumValues").value(Arrays.asList(LanguageCode.values())));

}
