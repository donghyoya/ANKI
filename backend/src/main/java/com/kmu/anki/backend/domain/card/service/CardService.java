package com.kmu.anki.backend.domain.card.service;

import com.kmu.anki.backend.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;


}
