package com.kmu.anki.backend.domain.usercard.docs.parameters;

import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class UserCardParameters {
    public static ParameterDescriptor cardId = parameterWithName("id").description("card 고유번호");

    public static ParameterDescriptor studyType = parameterWithName("studyType").description("study냐 review냐");
}
