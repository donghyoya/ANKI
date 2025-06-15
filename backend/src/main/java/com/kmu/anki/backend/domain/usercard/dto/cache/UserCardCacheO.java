package com.kmu.anki.backend.domain.usercard.dto.cache;

import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 캐시에 집어넣기 위한 임시 dto
 */
@Getter
public class UserCardCacheO {
    private List<Long> userCardIds;
    private Integer utcOffset;

    public UserCardCacheO(List<Long> userCardIds, Integer utcOffset) {
        this.userCardIds = userCardIds;
        this.utcOffset = utcOffset;
    }
}
