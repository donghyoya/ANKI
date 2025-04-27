package com.kmu.anki.backend.domain.user.dto;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import com.kmu.anki.backend.domain.user.entity.User;
import lombok.Getter;

@Getter
public class LoginUserDto {
    private Long id;
    private String name;
    private Role role;
    private boolean isFirst;

    public LoginUserDto(Long id, String name, Role role, boolean isFirst) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public static LoginUserDto of (User user){
        boolean isFirst = false;
        if (user.getDailyReviewWords() == null ||
                user.getDailyStudyWords() == null ||
                user.getLanguageCode() == null ||
                user.getUtcOffset() == null
        ){
            isFirst = true;
        }
        return new LoginUserDto(
                user.getId(),
                user.getName(),
                user.getRole(),
                isFirst
        );
    }
}
