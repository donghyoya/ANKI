package com.kmu.anki.backend.domain.usercard.dto.cache;

import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

/**
 * 캐시에 집어넣기 위한 임시 dto
 */
@Getter
public class UserCardCacheO {
    private Page<UserCardDto> userCardDtos;
    private Integer utcOffset;

    public UserCardCacheO(Page<UserCardDto> userCardDtos, Integer utcOffset) {
        this.userCardDtos = userCardDtos;
        this.utcOffset = utcOffset;
    }
}
