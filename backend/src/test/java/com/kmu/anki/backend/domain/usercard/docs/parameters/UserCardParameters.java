package com.kmu.anki.backend.domain.usercard.docs.parameters;

import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.Arrays;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class UserCardParameters {
    public static ParameterDescriptor cardId = parameterWithName("id").description("card 고유번호");

    public static ParameterDescriptor studyType = parameterWithName("studyType")
            .description("study냐 review냐")
            .attributes(Attributes.key("enumValues").value(Arrays.asList(StudyType.values())))
        ;
}
