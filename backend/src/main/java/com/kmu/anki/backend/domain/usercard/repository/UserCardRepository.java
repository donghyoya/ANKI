package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    boolean existsByUserId(@Param("userId") Long userId);

    long countByUserId(@Param("userId") Long userId);
}
