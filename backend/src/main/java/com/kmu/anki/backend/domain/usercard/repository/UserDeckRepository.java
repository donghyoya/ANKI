package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.usercard.dto.UserDeckDto;
import com.kmu.anki.backend.domain.usercard.entity.UserDeck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDeckRepository extends JpaRepository<UserDeck, Long> {
    @Query("select new com.kmu.anki.backend.domain.usercard.dto.UserDeckDto(d, count(c.id)) from UserDeck d left join d.cards c where d.userId = :userId group by d.id")
    Page<UserDeckDto> findUserDeckDtoByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("select new com.kmu.anki.backend.domain.usercard.dto.UserDeckDto(d, count(c.id)) from UserDeck d left join d.cards c where d.id = :id group by d.id")
    UserDeckDto findUserDeckDtoById(@Param("id") Long id);
}
