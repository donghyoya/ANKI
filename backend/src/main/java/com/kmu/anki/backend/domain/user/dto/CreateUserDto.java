package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.auth.vo.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CreateUserDto {
    private String loginId;
    private String loginPwd;
    private String name;
    private String email;
    private Role role;
}
