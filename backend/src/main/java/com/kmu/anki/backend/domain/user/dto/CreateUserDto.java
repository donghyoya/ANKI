package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserDto {
    private String loginId;
    private String loginPwd;
    private String name;
    private String email;
    private Role role;
}
