package com.kmu.anki.backend.domain.usercard.repository;

import com.kmu.anki.backend.domain.card.controller.option.QueryType;
import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import com.kmu.anki.backend.domain.usercard.controller.form.StudyType;
import com.kmu.anki.backend.domain.usercard.dto.DeckCheckDto;
import com.kmu.anki.backend.domain.usercard.dto.UserCardCheckDto;
import com.kmu.anki.backend.domain.usercard.dto.cache.UserCardCacheO;
import com.kmu.anki.backend.domain.usercard.repository.cache.UserCardCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 오늘 학습을 수행했는지에 대한 UserCardRepository
 */
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckDailyCardRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserCardCacheRepository userCardCacheRepository;
    private final UserCardQueryRepository userCardQueryRepository;

    private static String CHECK_LEARNING_QUERY = """
        SELECT count(user_card_id)
        FROM user_cards uc
        WHERE uc.user_card_id in ?
            AND (
                user_card_state != 'New' 
            );
    """;

    private static String CHECK_REVIEW_QUERY = """
        SELECT count(user_card_id)
        FROM user_cards uc
        WHERE uc.user_card_id in ?
            AND (
                user_card_state != 'Review' 
            );
    """;

    public boolean checkLearning(List<Long> userCardIds){
        Integer count = jdbcTemplate.queryForObject(CHECK_LEARNING_QUERY, Integer.class, userCardIds);
        return count>0;
    }

    public boolean checkReview(List<Long> userCardIds){
        Integer count = jdbcTemplate.queryForObject(CHECK_REVIEW_QUERY, Integer.class, userCardIds);
        return count>0;
    }

    /* 덱 관련 */

    public Map<Object, DeckCheckDto> checkDeckCards(Long userId, QueryType queryType){
        // 1. Key별로 Map을 만든다. Key는 Level 또는 Topic으로 한다. 즉 Map의 Value값안에 Study 및 Review가 포함되어야한다
        // 2. 1을 만드는 동시에 Set도 만든다
        // 3. 2에서 만든 Set으로 batch 쿼리를 수행한다
        // 4. 1에서 만든 Key를 가져와서 3에서 가져온 batch값을 매핑한다
        // 5. 4에서 구성된 것을 바탕으로 Key값에 대한 2개의 boolean값을 설정한다.

        // 1. Key별로 Map을 만든다. Key는 Level 또는 Topic으로 한다. 즉 Map의 Value값안에 Study 및 Review가 포함되어야한다
        // 2. 1을 만드는 동시에 Set도 만든다
        Map<Object, DeckCheckDto> map = new HashMap<>();
        Set<Long> set = new HashSet<>();
        if(queryType == QueryType.LEVEL){
            for(CardLevel level : CardLevel.values()){
                List<Long> studyCards = null;
                Optional<UserCardCacheO> cacheO = userCardCacheRepository.findDailyUserCard(userId, StudyType.study, level);
                if(cacheO.isPresent()) {
                    studyCards = cacheO.get().getUserCardIds();
                    set.addAll(studyCards);
                }

                List<Long> reviewCards = null;
                cacheO = userCardCacheRepository.findDailyUserCard(userId, StudyType.review, level);
                if(cacheO.isPresent()) {
                    reviewCards = cacheO.get().getUserCardIds();
                    set.addAll(reviewCards);
                }

                map.put(level, new DeckCheckDto(studyCards, reviewCards));
            }
        }else {
            for(CardTopicEnums topic : CardTopicEnums.values()){
                List<Long> studyCards = null;
                Optional<UserCardCacheO> cacheO = userCardCacheRepository.findDailyUserCard(userId, StudyType.study, topic);
                if(cacheO.isPresent()) {
                    studyCards = cacheO.get().getUserCardIds();
                    set.addAll(studyCards);
                }

                List<Long> reviewCards = null;
                cacheO = userCardCacheRepository.findDailyUserCard(userId, StudyType.review, topic);
                if(cacheO.isPresent()) {
                    reviewCards = cacheO.get().getUserCardIds();
                    set.addAll(reviewCards);
                }

                map.put(topic, new DeckCheckDto(studyCards, reviewCards));
            }
        }

        // 3. 2에서 만든 Set으로 batch 쿼리를 수행한다
        List<UserCardCheckDto> userCardCheckDto = userCardQueryRepository.findUserCardCheckDto(set);

        // 4. 1에서 만든 Key를 가져와서 3에서 가져온 batch값을 매핑한다
        Map<Long, UserCardCheckDto> idMap = userCardCheckDto.stream().collect(Collectors.toMap(UserCardCheckDto::getUserId, dto -> dto));


        // 5. 4에서 구성된 것을 바탕으로 Key값에 대한 2개의 boolean값을 설정한다.
        for(DeckCheckDto value : map.values()){
            value.check(idMap);
        }

        return map;
    }
}
