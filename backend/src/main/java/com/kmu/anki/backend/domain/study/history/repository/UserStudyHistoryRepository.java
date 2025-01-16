package com.kmu.anki.backend.domain.study.history.repository;

import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyHistoryRepository extends JpaRepository<UserStudyHistory, Long> {
}
