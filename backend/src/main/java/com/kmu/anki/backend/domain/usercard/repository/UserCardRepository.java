package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
}
