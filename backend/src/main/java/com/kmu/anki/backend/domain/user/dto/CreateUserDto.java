package com.kmu.anki.backend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    public String loginId;
    public String loginPwd;
}
