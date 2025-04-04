package com.kmu.anki.backend.domain.auth.legacy.vo;

import lombok.Data;

@Data
public class LoginVO {
    // TODO 미사용인데 왜 존재하는 것인지?  
    public String loginId;
    public String loginPwd;
}
