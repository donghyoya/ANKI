package com.kmu.anki.backend.domain.usercard.controller.form;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyCardForm {
    private Integer score;
    private LocalDateTime nextStudyDate;
}
