package com.kmu.anki.backend.global;

import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ExceptionResponseDocs {
    public static FieldDescriptor[] exceptionResponse = new FieldDescriptor[]{
            fieldWithPath("code").description("에러코드(http상태코드)"),
            fieldWithPath("message").optional().description("에러에 대한 메시지"),
    };

    public static final Schema exceptionResponseSchema = new Schema("exceptionResponse");

}
