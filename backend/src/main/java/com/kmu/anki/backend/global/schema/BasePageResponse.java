package com.kmu.anki.backend.global.schema;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class BasePageResponse<T>{
    private int page;
    private int pageSize;
    private int size;
    private List<T> content;

    public BasePageResponse(Page<T> data) {
        this.content = data.getContent();
        this.size = data.getNumberOfElements();
        this.page = data.getNumber();
        this.pageSize = data.getTotalPages();
    }

    public static <T> BasePageResponse<T> of(Page<T> data){
        return new BasePageResponse<>(data);
    }
}
