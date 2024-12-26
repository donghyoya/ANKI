package com.kmu.anki.backend.domain.card.repository;

import com.kmu.anki.backend.domain.card.entity.ForeignCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForeignCardRepository extends JpaRepository<ForeignCard, Long> {
}
