package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    List<UserCard> findByDeckId(Long deckId);
}
