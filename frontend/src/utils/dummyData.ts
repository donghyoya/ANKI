import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { MenuItem } from '@/types/Menu';
import { ProgressBarSegment } from '@/types/ProgressBarSegment';

import {
  Card,
  KoreanCard,
  KoreanCardDetail,
  StudyInfo,
  UserOption,
  UserStudyHistory,
  Deck,
  StudyInfoDTO,
  Paginated,
  UserCard,
  KoreanCardWithForeignWords
} from '@/types/schemes';
import { State } from 'ts-fsrs';

export const DUMMY_PROGRESS: ProgressBarSegment[] = [
  { value: 10, label: '10', tooltip: 'Matured', color: LEARNING_PROGRESS_BAR_COLORS.Review },
  { value: 20, label: '20', tooltip: 'Learning', color: LEARNING_PROGRESS_BAR_COLORS.Learning },
  { value: 30, label: '30', tooltip: 'New', color: LEARNING_PROGRESS_BAR_COLORS.New }
];

export const DUMMY_MENU_ITEMS: MenuItem[] = [
  { label: '사과', onClick: () => {} },
  { label: '바나나', onClick: () => {} },
  { label: '오렌지', onClick: () => {} }
];

export const LANGUAGE_OPTIONS = [
  { code: 'ar', label: 'العربية' }, // 아랍어
  { code: 'en', label: 'English' }, // 영어
  { code: 'es', label: 'Español' }, // 스페인어
  { code: 'fr', label: 'Français' }, // 프랑스어
  { code: 'id', label: 'Bahasa Indonesia' }, // 인도네시아어
  { code: 'ja', label: '日本語' }, // 일본어
  { code: 'mn', label: 'Монгол' }, // 몽골어
  { code: 'ru', label: 'Русский' }, // 러시아어
  { code: 'th', label: 'ไทย' }, // 태국어
  { code: 'vi', label: 'Tiếng Việt' }, // 베트남어
  { code: 'zh', label: '中文' } // 중국어
];

export const UTC_OFFSET_OPTIONS = [
  { code: 0, label: 'UTC+00:00' },
  { code: 1, label: 'UTC+01:00' },
  { code: 2, label: 'UTC+02:00' },
  { code: 3, label: 'UTC+03:00' },
  { code: 4, label: 'UTC+04:00' }
];

export const THEME_OPTIONS = [
  { value: 'light', messageKey: 'settings.light' },
  { value: 'dark', messageKey: 'settings.dark' },
  { value: 'lightMediumContrast', messageKey: 'settings.lightMediumContrast' },
  { value: 'darkMediumContrast', messageKey: 'settings.darkMediumContrast' },
  { value: 'lightHighContrast', messageKey: 'settings.lightHighContrast' },
  { value: 'darkHighContrast', messageKey: 'settings.darkHighContrast' }
];

// Dummy data for api mocking
export const DUMMY_CARD: Card = {
  difficulty: 'easy',
  cardId: 1,
  koreanWord: '사랑',
  languageCode: 'en',
  foreignWord: 'love'
};

export const DUMMY_KOR_CARD: KoreanCard = {
  cardId: 1,
  koreanWord: '가깝다',
  homographNumber: 1,
  level: 'easy',
  topics: []
};

export const DUMMY_KOR_CARD_WITH_FOREIGN_WORDS: KoreanCardWithForeignWords = {
  ...DUMMY_KOR_CARD,
  foreignWords: ['near; close; adjacent']
};

export const DUMMY_KOR_CARD_DETAIL: KoreanCardDetail = {
  ...DUMMY_KOR_CARD,
  meanings: [
    {
      foreignWord: 'near; close; adjacent',
      foreignMeaning: 'near; close; adjacent',
      languageCode: 'ko',
      originalLanguage: '家具',
      partsOfSpeech: '(adj.)',
      pronunciation: '가깝따',
      relatedWords: '반댓말 멀다2 반댓말 멀다2, 멀다2',
      inflection: '가까운, 가꾸어(가꿔), 가까우니, 가깝습니다',
      exampleUsage:
        '<구> 안녕하세요\n<구> 안녕하세요\n<구> 안녕하세요\n<문> 안녕하세요\n<문> 안녕하세요\n<문> 안녕하세요\n<대화> 안녕하세요\n<대화> 안녕하세요\n<대화> 안녕하세요'
    }
  ]
};

export const DUMMY_CARDS: Paginated<Card> = {
  size: 3,
  pageSize: 1,
  page: 1,
  content: [DUMMY_CARD, DUMMY_CARD, DUMMY_CARD]
};

export const DUMMY_KOR_CARD_DETAILS: Paginated<KoreanCardDetail> = {
  size: 1,
  pageSize: 1,
  page: 1,
  content: [DUMMY_KOR_CARD_DETAIL, DUMMY_KOR_CARD_DETAIL, DUMMY_KOR_CARD_DETAIL]
};

export const DUMMY_STUDY_INFO: StudyInfo = {
  due: new Date(),
  lapses: 0,
  reps: 0,
  scheduledDays: 0,
  lastReview: new Date('2025-01-01'),
  state: State.New,
  stability: 0,
  difficulty: 0,
  elapsedDays: 0,
  learningSteps: 0
};

export const DUMMY_STUDY_INFO_DTO: StudyInfoDTO = {
  lapses: 0,
  reps: 0,
  due: new Date(),
  scheduledDays: 0,
  lastReview: '2025-01-01',
  state: 'New',
  stability: 0,
  difficulty: 0
};

export const DUMMY_USER_CARD: UserCard = {
  koreanCard: DUMMY_KOR_CARD,
  studyInfo: DUMMY_STUDY_INFO,
  userCardId: 1
};

export const DUMMY_USER_CARDS: Paginated<UserCard> = {
  size: 10,
  pageSize: 1,
  page: 1,
  content: [
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '하나', cardId: 1 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '둘', cardId: 2 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '셋', cardId: 3 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '넷', cardId: 4 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '다섯', cardId: 5 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '여섯', cardId: 6 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '일곱', cardId: 7 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '여덟', cardId: 8 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '아홉', cardId: 9 } },
    { ...DUMMY_USER_CARD, koreanCard: { ...DUMMY_KOR_CARD, koreanWord: '열', cardId: 10 } }
  ]
};

export const DUMMY_DECK: Deck = {
  cardCounts: 100,
  learningCounts: 10,
  newCounts: 10,
  overdueCounts: 10,
  maturityCounts: 10,
  category: 'easy'
};

export const DUMMY_DECKS: Paginated<Deck> = {
  size: 3,
  pageSize: 1,
  page: 1,
  content: [
    { ...DUMMY_DECK, category: 'easy' },
    { ...DUMMY_DECK, category: 'normal' },
    { ...DUMMY_DECK, category: 'hard' }
  ]
};

export const DUMMY_USER_STUDY_HISTORY: UserStudyHistory = {
  deckType: 'level',
  studyType: 'review',
  deckName: 'easy',
  studyDate: '2025-01-01'
};

export const DUMMY_USER_STUDY_HISTORIES: Paginated<UserStudyHistory> = {
  size: 2,
  pageSize: 1,
  page: 1,
  content: [DUMMY_USER_STUDY_HISTORY, DUMMY_USER_STUDY_HISTORY]
};

export const DUMMY_USER_OPTION: UserOption = {
  dailyReviewWords: 0,
  dailyStudyWords: 0,
  utcOffset: 0,
  languageCode: 'en'
};
