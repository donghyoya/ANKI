package com.kmu.anki.backend.domain.study.history.repository;

import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyHistoryRepository extends JpaRepository<UserStudyHistory, Long> {
    Page<UserStudyHistory> findByUserIdOrderByStudyDateDesc(@Param("userId") Long userId, Pageable pageable);
}
