-- 예제 데이터 입력
-- Easy 난이도 Korean Cards
INSERT INTO korean_cards (korean_card_id, level, korean_word)
VALUES
    (1, 'easy', '안녕'),
    (2, 'easy', '사과'),
    (3, 'easy', '물'),
    (4, 'easy', '책'),
    (5, 'easy', '밥'),
    (6, 'easy', '학교'),
    (7, 'easy', '공'),
    (8, 'easy', '연필'),
    (9, 'easy', '의자'),
    (10, 'easy', '친구'),
    (11, 'easy', '우유'),
    (12, 'easy', '하늘'),
    (13, 'easy', '강아지'),
    (14, 'easy', '고양이'),
    (15, 'easy', '집'),
    (16, 'easy', '사람'),
    (17, 'easy', '나무'),
    (18, 'easy', '바다'),
    (19, 'easy', '시간'),
    (20, 'easy', '꽃');

-- Normal 난이도 Korean Cards
INSERT INTO korean_cards (korean_card_id, level, korean_word)
VALUES
    (21, 'normal', '사랑'),
    (22, 'normal', '희망'),
    (23, 'normal', '추억'),
    (24, 'normal', '인생'),
    (25, 'normal', '세상'),
    (26, 'normal', '행복'),
    (27, 'normal', '가족'),
    (28, 'normal', '음악'),
    (29, 'normal', '여행'),
    (30, 'normal', '노래'),
    (31, 'normal', '그림'),
    (32, 'normal', '빛'),
    (33, 'normal', '어둠'),
    (34, 'normal', '별'),
    (35, 'normal', '바람'),
    (36, 'normal', '꿈'),
    (37, 'normal', '산'),
    (38, 'normal', '강'),
    (39, 'normal', '겨울'),
    (40, 'normal', '여름');

-- Hard 난이도 Korean Cards
INSERT INTO korean_cards (korean_card_id, level, korean_word)
VALUES
    (41, 'hard', '불가사의'),
    (42, 'hard', '천둥'),
    (43, 'hard', '번개'),
    (44, 'hard', '우주'),
    (45, 'hard', '행성'),
    (46, 'hard', '철학'),
    (47, 'hard', '사상'),
    (48, 'hard', '문학'),
    (49, 'hard', '예술'),
    (50, 'hard', '역사'),
    (51, 'hard', '기술'),
    (52, 'hard', '과학'),
    (53, 'hard', '수학'),
    (54, 'hard', '논리'),
    (55, 'hard', '법률'),
    (56, 'hard', '사회'),
    (57, 'hard', '경제'),
    (58, 'hard', '정치'),
    (59, 'hard', '철학자'),
    (60, 'hard', '우주선');

-- Easy 난이도 Foreign Cards (영어와 일본어)
INSERT INTO foreign_cards (foreign_word, korean_card_id, language_code)
VALUES
    ('hello', 1, 'en'),         -- 안녕
    ('apple', 2, 'en'),         -- 사과
    ('water', 3, 'en'),         -- 물
    ('book', 4, 'en'),          -- 책
    ('rice', 5, 'en'),          -- 밥
    ('school', 6, 'en'),        -- 학교
    ('ball', 7, 'en'),          -- 공
    ('pencil', 8, 'en'),        -- 연필
    ('chair', 9, 'en'),         -- 의자
    ('friend', 10, 'en'),       -- 친구
    ('milk', 11, 'en'),         -- 우유
    ('sky', 12, 'en'),          -- 하늘
    ('puppy', 13, 'en'),        -- 강아지
    ('cat', 14, 'en'),          -- 고양이
    ('house', 15, 'en'),        -- 집
    ('person', 16, 'en'),       -- 사람
    ('tree', 17, 'en'),         -- 나무
    ('sea', 18, 'en'),          -- 바다
    ('time', 19, 'en'),         -- 시간
    ('flower', 20, 'en'),       -- 꽃
    ('こんにちは', 1, 'ja'),    -- 안녕
    ('りんご', 2, 'ja'),        -- 사과
    ('みず', 3, 'ja'),          -- 물
    ('ほん', 4, 'ja'),          -- 책
    ('ごはん', 5, 'ja'),        -- 밥
    ('がっこう', 6, 'ja'),      -- 학교
    ('ボール', 7, 'ja'),        -- 공
    ('えんぴつ', 8, 'ja'),      -- 연필
    ('いす', 9, 'ja'),          -- 의자
    ('ともだち', 10, 'ja'),     -- 친구
    ('ぎゅうにゅう', 11, 'ja'), -- 우유
    ('そら', 12, 'ja'),          -- 하늘
    ('こいぬ', 13, 'ja'),        -- 강아지
    ('ねこ', 14, 'ja'),          -- 고양이
    ('いえ', 15, 'ja'),          -- 집
    ('ひと', 16, 'ja'),          -- 사람
    ('き', 17, 'ja'),            -- 나무
    ('うみ', 18, 'ja'),          -- 바다
    ('じかん', 19, 'ja'),        -- 시간
    ('はな', 20, 'ja');          -- 꽃

-- Normal 난이도 Foreign Cards (영어와 일본어)
INSERT INTO foreign_cards (foreign_word, korean_card_id, language_code)
VALUES
    ('love', 21, 'en'),         -- 사랑
    ('hope', 22, 'en'),         -- 희망
    ('memory', 23, 'en'),       -- 추억
    ('life', 24, 'en'),         -- 인생
    ('world', 25, 'en'),        -- 세상
    ('happiness', 26, 'en'),    -- 행복
    ('family', 27, 'en'),       -- 가족
    ('music', 28, 'en'),        -- 음악
    ('travel', 29, 'en'),       -- 여행
    ('song', 30, 'en'),         -- 노래
    ('painting', 31, 'en'),     -- 그림
    ('light', 32, 'en'),        -- 빛
    ('darkness', 33, 'en'),     -- 어둠
    ('star', 34, 'en'),         -- 별
    ('wind', 35, 'en'),         -- 바람
    ('dream', 36, 'en'),        -- 꿈
    ('mountain', 37, 'en'),     -- 산
    ('river', 38, 'en'),        -- 강
    ('winter', 39, 'en'),       -- 겨울
    ('summer', 40, 'en'),       -- 여름
    ('あい', 21, 'ja'),          -- 사랑
    ('きぼう', 22, 'ja'),        -- 희망
    ('おもいで', 23, 'ja'),      -- 추억
    ('じんせい', 24, 'ja'),      -- 인생
    ('せかい', 25, 'ja'),        -- 세상
    ('しあわせ', 26, 'ja'),      -- 행복
    ('かぞく', 27, 'ja'),        -- 가족
    ('おんがく', 28, 'ja'),      -- 음악
    ('りょこう', 29, 'ja'),      -- 여행
    ('うた', 30, 'ja'),          -- 노래
    ('え', 31, 'ja'),            -- 그림
    ('ひかり', 32, 'ja'),        -- 빛
    ('やみ', 33, 'ja'),          -- 어둠
    ('ほし', 34, 'ja'),          -- 별
    ('かぜ', 35, 'ja'),          -- 바람
    ('ゆめ', 36, 'ja'),          -- 꿈
    ('やま', 37, 'ja'),          -- 산
    ('かわ', 38, 'ja'),          -- 강
    ('ふゆ', 39, 'ja'),          -- 겨울
    ('なつ', 40, 'ja');          -- 여름

-- Hard 난이도 Foreign Cards (영어와 일본어)
INSERT INTO foreign_cards (foreign_word, korean_card_id, language_code)
VALUES
    ('mystery', 41, 'en'),      -- 불가사의
    ('thunder', 42, 'en'),      -- 천둥
    ('lightning', 43, 'en'),    -- 번개
    ('universe', 44, 'en'),     -- 우주
    ('planet', 45, 'en'),       -- 행성
    ('philosophy', 46, 'en'),   -- 철학
    ('thought', 47, 'en'),      -- 사상
    ('literature', 48, 'en'),   -- 문학
    ('art', 49, 'en'),          -- 예술
    ('history', 50, 'en'),      -- 역사
    ('technology', 51, 'en'),   -- 기술
    ('science', 52, 'en'),      -- 과학
    ('mathematics', 53, 'en'),  -- 수학
    ('logic', 54, 'en'),        -- 논리
    ('law', 55, 'en'),          -- 법률
    ('society', 56, 'en'),      -- 사회
    ('economy', 57, 'en'),      -- 경제
    ('politics', 58, 'en'),     -- 정치
    ('philosopher', 59, 'en'),  -- 철학자
    ('spaceship', 60, 'en'),    -- 우주선
    ('ふしぎ', 41, 'ja'),        -- 불가사의
    ('かみなり', 42, 'ja'),      -- 천둥
    ('いなずま', 43, 'ja'),      -- 번개
    ('うちゅう', 44, 'ja'),      -- 우주
    ('わくせい', 45, 'ja'),      -- 행성
    ('てつがく', 46, 'ja'),      -- 철학
    ('しそう', 47, 'ja'),        -- 사상
    ('ぶんがく', 48, 'ja'),      -- 문학
    ('げいじゅつ', 49, 'ja'),    -- 예술
    ('れきし', 50, 'ja'),        -- 역사
    ('ぎじゅつ', 51, 'ja'),      -- 기술
    ('かがく', 52, 'ja'),        -- 과학
    ('すうがく', 53, 'ja'),      -- 수학
    ('ろんり', 54, 'ja'),        -- 논리
    ('ほうりつ', 55, 'ja'),      -- 법률
    ('しゃかい', 56, 'ja'),      -- 사회
    ('けいざい', 57, 'ja'),      -- 경제
    ('せいじ', 58, 'ja'),        -- 정치
    ('てつがくしゃ', 59, 'ja'), -- 철학자
    ('うちゅうせん', 60, 'ja');  -- 우주선



insert into users(user_id, today_study_words) values (1, 200);
insert into users(user_id, today_study_words) values (2, 200);

INSERT INTO user_cards(korean_card_id, user_id, user_card_state)
SELECT korean_cards.korean_card_id, 1, 'New'
FROM korean_cards;
