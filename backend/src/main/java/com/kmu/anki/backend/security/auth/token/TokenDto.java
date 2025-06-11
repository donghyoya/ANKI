package com.kmu.anki.backend.security.auth.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {
    private String accessToken;
    private String refreshToken;


    private boolean isSetup;

    @JsonProperty("isSetup")
    public boolean isSetup(){
        return isSetup;
    }

    public TokenDto(String accessToken, String refreshToken, boolean isSetup) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isSetup  = isSetup;
    }
}
