package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
