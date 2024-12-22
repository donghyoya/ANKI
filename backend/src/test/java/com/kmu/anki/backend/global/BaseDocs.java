package com.kmu.anki.backend.global;

import org.springframework.restdocs.payload.FieldDescriptor;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class BaseDocs {
    private static FieldDescriptor[] _basePageResponse = new FieldDescriptor[]{
            fieldWithPath("size").description("content의 크기"),
            fieldWithPath("content").description("실제 데이터들"),
            fieldWithPath("page").description("현재 페이지의 번호"),
            fieldWithPath("pageSize").description("전체 페이지의 크기")
    };

    public static FieldDescriptor[] basePageResponse(){
        return _basePageResponse;
    }

    private static FieldDescriptor[] _baseListResponse = new FieldDescriptor[]{
            fieldWithPath("size").description("content의 크기"),
            fieldWithPath("content").description("실제 데이터들"),
    };

    public static FieldDescriptor[] baseListResponse(){
        return _baseListResponse;
    }

    public static String basePageResponsePrefix = "content[].";

    public static FieldDescriptor[] combine(FieldDescriptor[] a, FieldDescriptor[] b){
        List<FieldDescriptor> ret = new ArrayList<>();
        Collections.addAll(ret,a);
        Collections.addAll(ret,b);
        return ret.toArray(FieldDescriptor[]::new);
    }
}
