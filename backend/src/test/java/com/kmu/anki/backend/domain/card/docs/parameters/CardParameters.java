package com.kmu.anki.backend.domain.card.docs.parameters;

import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class CardParameters {
    /* parameters */
    public static ParameterDescriptor cardId = parameterWithName("id").description("카드의 고유번호");

}
