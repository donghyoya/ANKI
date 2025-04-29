package com.kmu.anki.backend.domain.usercard.entity;

import com.kmu.anki.backend.domain.card.entity.KoreanCard;
import com.kmu.anki.backend.domain.user.entity.CardState;
import com.kmu.anki.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_cards")
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_card_id")
    private Long id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime due;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer lapses;

    @Column
    private LocalDateTime lastReview;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer reps;

    @Column(columnDefinition = "FLOAT DEFAULT 0")
    private Double scheduledDays;

    @Column(columnDefinition = "FLOAT DEFAULT 0")
    private Double stability;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_card_state")
    private CardState state;

    @Column(columnDefinition = "FLOAT DEFAULT 0")
    private Double difficulty;

    /* 관계 User */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public void mapUser(User user){
        this.user = user;
        this.user.addCard(this);
    }

    /* 관계 - KoeranCard */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korean_card_id")
    private KoreanCard koreanCard;

    @Column(name = "korean_card_id", insertable = false, updatable = false)
    private Long koreanCardId;

    /* 로직 */
    public void update(LocalDateTime due, Integer lapses, LocalDateTime lastReview, Integer reps, Double scheduledDays, Double stability, Double difficulty, CardState state){
        this.due = due;
        this.lapses = lapses;
        this.lastReview = lastReview;
        this.reps = reps;
        this.scheduledDays = scheduledDays;
        this.stability = stability;
        this.difficulty = difficulty;
        this.state = state;
    }
}
