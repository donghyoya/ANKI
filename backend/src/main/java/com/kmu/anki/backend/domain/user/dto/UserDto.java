package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String name;

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserDto of(User user){
        return new UserDto(user.getId(), user.getName());
    }
}
