package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    List<UserCard> findByDeckId(Long deckId);

    long countByDeckId(Long deckId);

    @Query("""
        select new com.kmu.anki.backend.domain.usercard.dto.UserCardDto(uc, c)
        from UserCard uc join fetch uc.card c
        where uc.deckId = :deckId and uc.nextStudyDate < :now
    """)
    List<UserCardDto> findTodayStudyCard(@Param("deckId") Long deckId, @Param("now") LocalDateTime now);

    @Query("""
        select new com.kmu.anki.backend.domain.usercard.dto.UserCardDto(uc, c)
        from UserCard uc join fetch uc.card c
        where uc.deckId = :deckId
    """)
    List<UserCardDto> findCard(@Param("deckId") Long deckId);

}
