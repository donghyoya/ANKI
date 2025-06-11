package com.kmu.anki.backend.domain.user.entity;

import com.kmu.anki.backend.domain.auth.legacy.vo.Role;
import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import com.kmu.anki.backend.domain.study.history.entity.UserStudyHistory;
import com.kmu.anki.backend.domain.user.dto.CreateUserDto;
import com.kmu.anki.backend.domain.usercard.entity.UserCard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private Integer dailyStudyWords;

    @Column
    private Integer dailyReviewWords;

    @Column
    private String email;

    @Column
    private String name;

    @Column(unique = true, name = "login_id")
    private String loginId;

    @Column(name = "login_pwd")
    private String loginPwd;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageCode languageCode;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer utcOffset;

    @Column
    private boolean firstLogin;

    public void update(Integer dailyStudyWords, Integer dailyReviewWords,LanguageCode languageCode, Integer utcOffset){
        this.dailyStudyWords = dailyStudyWords;
        this.dailyReviewWords = dailyReviewWords;
        this.languageCode = languageCode;
        this.utcOffset = utcOffset;
    }

    public boolean isSetup(){
        // 하나라도 null이면 false
        return this.dailyReviewWords != null && this.dailyStudyWords != null && this.languageCode != null && this.utcOffset != null;
    }

    /* 관계 : 유저덱 */

    /**
     * 앵간하면 사용하지 말고 따로 쿼리(UserCard 기준으로 쿼리)할 것
     */
    @OneToMany(mappedBy = "user")
    private List<UserCard> cards = new ArrayList<>();

    public void addCard(UserCard card){
        cards.add(card);
    }

    /* 관계 UserStudyHistory */
    @OneToMany(mappedBy = "user")
    private List<UserStudyHistory> studyHistories = new ArrayList<>();

    public void addStudyHistory(UserStudyHistory studyHistory){
        studyHistories.add(studyHistory);
    }

    /* 생성 */

    public User(Integer todayStudyWords, Integer todayReviewWords) {
        this(todayStudyWords, todayReviewWords, LanguageCode.en);
    }

    public User(Integer dailyStudyWords, Integer dailyReviewWords, LanguageCode languageCode) {
        this.dailyStudyWords = dailyStudyWords;
        this.dailyReviewWords = dailyReviewWords;
        this.languageCode = languageCode;
        this.firstLogin = true;
    }

    public User(CreateUserDto dto){
        this();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.loginId = dto.getLoginId();
        this.loginPwd = dto.getLoginPwd();
        this.role = dto.getRole();
        this.dailyStudyWords = 30;
        this.dailyReviewWords = 10;
        this.languageCode = LanguageCode.en;
    }
}
