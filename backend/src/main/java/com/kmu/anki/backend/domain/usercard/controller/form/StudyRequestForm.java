package com.kmu.anki.backend.domain.usercard.controller.form;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import lombok.Getter;

@Getter
public class StudyRequestForm {
    private LanguageCode languageCode;
    private CardDifficulty difficulty;
}
