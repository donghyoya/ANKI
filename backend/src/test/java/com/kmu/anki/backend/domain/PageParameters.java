package com.kmu.anki.backend.domain;

import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class PageParameters {
    public static ParameterDescriptor page = parameterWithName("page").description("Page 넘버 (1부터 시작)");
    public static ParameterDescriptor pageSize = parameterWithName("pageSize").description("페이지에 포함된 컨텐츠 크기");

}
