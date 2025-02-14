package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.auth.vo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    /*
     TODO
        why public? why setter
        1. setter 대신 lombok의 빌더패턴을 사용할 것 (그것이 더 안전함)
        2. public으로 하는 저의를 모르겠음. getter setter의 의미가 있나?
     */


    public String loginId;
    public String loginPwd;
    public String name;
    public String email;
    public Role role;
}
