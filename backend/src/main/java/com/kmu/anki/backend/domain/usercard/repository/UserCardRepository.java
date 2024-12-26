package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.enums.CardDifficulty;
import com.kmu.anki.backend.domain.card.enums.CardMeaningGroup;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.usercard.dto.UserCardDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    @Query("""
        select new com.kmu.anki.backend.domain.usercard.dto.UserCardDto(uc, c)
        from UserCard uc join fetch uc.card c
        where uc.id = :userCardId
    """)
    UserCardDto findCardByUserCardId(@Param("userCardId") Long userCardId);

    @Query("""
        select new com.kmu.anki.backend.domain.usercard.dto.UserCardDto(uc, c)
        from UserCard uc join fetch uc.card c
        where uc.userId = :userId 
            and c.languageCode = :languageCode 
            and c.difficulty = :cardDifficulty 
            and uc.nextStudyDate <= :now
       """)
    Page<UserCardDto> findStudyCard(
            @Param("userId") Long userId,
            @Param("languageCode") LanguageCode languageCode,
            @Param("cardDifficulty") CardDifficulty cardDifficulty,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );

    @Query("""
        select new com.kmu.anki.backend.domain.usercard.dto.UserCardDto(uc, c)
        from UserCard uc join fetch uc.card c
        where uc.userId = :userId 
            and c.languageCode = :languageCode 
            and c.meaningGroup = :meaningGroup 
            and uc.nextStudyDate <= :now
       """)
    Page<UserCardDto> findStudyCard(
            @Param("userId") Long userId,
            @Param("languageCode") LanguageCode languageCode,
            @Param("cardDifficulty") CardMeaningGroup meaningGroup,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );


    @Query("""
        select uc
        from UserCard uc join fetch uc.card c
        where uc.id = :userCardId
    """)
    UserCard findUserCardById(@Param("userCardId") Long userCardId);

    boolean existsByUserId(@Param("userId") Long userId);

    long countByUserId(@Param("userId") Long userId);
}
