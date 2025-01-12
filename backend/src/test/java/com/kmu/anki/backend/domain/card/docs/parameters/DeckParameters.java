package com.kmu.anki.backend.domain.card.docs.parameters;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.*;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class DeckParameters {
    public static ParameterDescriptor queryType = parameterWithName("queryType")
            .description("난이도 기준 검색인지, 의미상 구분에 따른 검색인지")
            .attributes(Attributes.key("enumValues").value(Arrays.asList(QueryType.values())));

    public static ParameterDescriptor query = parameterWithName("query")
            .description("검색어 (easy-normal-hard 등)")
            .attributes(Attributes.key("enumValues").value(queryEnums()))
    ;

    private static List<Object> queryEnums(){
        List<Object> values = new ArrayList<>();
        values.addAll(Arrays.asList(CardLevel.values()));
        values.addAll(Arrays.asList(CardTopicEnums.values()));
        return values;
    }
}
