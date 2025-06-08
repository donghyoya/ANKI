package com.kmu.anki.backend.domain.card.controller;

import com.kmu.anki.backend.domain.card.controller.option.ForeignCardSearchOption;
import com.kmu.anki.backend.domain.card.dto.ForeignCardSearchResult;
import com.kmu.anki.backend.domain.card.dto.KoreanCardDto;
import com.kmu.anki.backend.domain.card.service.CardSearchService;
import com.kmu.anki.backend.domain.user.service.UserOptionService;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardSearchController {
    private final UserOptionService userOptionService;
    private final CardSearchService cardSearchService;

    @GetMapping("/korean-search")
    public BasePageResponse<KoreanCardDto> searchKoreanCard(
            @RequestParam("query") String query,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ){
        return BasePageResponse.of(cardSearchService.searchKoreanCardByKoreanWord(query, page-1, pageSize));
    }

    @GetMapping("/foreign-search")
    public BasePageResponse<ForeignCardSearchResult> searchForeignWord(
            @RequestParam("query") String query,
            @RequestParam("option") ForeignCardSearchOption option,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ){
        return BasePageResponse.of(cardSearchService.searchForeignCard(query, option, page-1, pageSize));
    }

}
