package com.kmu.anki.backend.domain.usercard.controller;

import com.kmu.anki.backend.domain.card.controller.QueryType;
import com.kmu.anki.backend.domain.card.dto.DeckDto;
import com.kmu.anki.backend.domain.card.enums.CardCategory;
import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyRequestForm;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.dto.UserDeckDto;
import com.kmu.anki.backend.domain.usercard.service.UserDeckService;
import com.kmu.anki.backend.global.schema.BaseListReponse;
import com.kmu.anki.backend.global.schema.BasePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/decks/study")
@RestController
public class UserDeckController {
    private final UserDeckService userDeckService;

    @GetMapping
    public BasePageResponse<UserDeckDto> getMyDeck(){
        // TODO userId 조작하기
        Page<UserDeckDto> decks = userDeckService.findUserDeckByUserId(1L);
        return BasePageResponse.of(decks);
    }

    @PostMapping
    public UserDeckDto createStudyDeck(
            @RequestBody StudyRequestForm form
    ){
        // TODO userId 조치하기
        UserDeckDto response = userDeckService.study(form.getLanguageCode(), form.getDifficulty(), 1L);
        return response;
    }

    @GetMapping("/{deckId}")
    public BaseListReponse<UserCardDto> getDecksCard(@PathVariable("deckId") Long deckId){
        List<UserCardDto> cards = userDeckService.findCard(deckId);
        return BaseListReponse.of(cards);
    }

    @GetMapping("/{deckId}/study")
    public BaseListReponse<UserCardDto> getDecksCard(
            @PathVariable("deckId") Long deckId,
            @RequestParam("studyType") StudyType type
    ){
        List<UserCardDto> cards;
        if(type == StudyType.Study){
            cards = userDeckService.findTodayStudyCard(deckId);
        }else {
            cards = userDeckService.findTodayReviewCard(deckId);
        }
        return BaseListReponse.of(cards);
    }

}
