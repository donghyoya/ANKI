package com.kmu.anki.backend.global.schema;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class BaseListReponse<T>{
    private int size;
    private List<T> content;

    public BaseListReponse(List<T> data) {
        this.content = data;
        this.size = data.size();
    }

    public static <T> BaseListReponse<T> of(List<T> data){
        return new BaseListReponse<>(data);
    }

}
