package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.auth.vo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    public String loginId;
    public String loginPwd;
    public String name;
    public String email;
    public Role role;
}
