package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoreanCardRepository extends JpaRepository<KoreanCard, Long> {
}
