package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeckRepository extends JpaRepository<UserDeck, Long> {
}
