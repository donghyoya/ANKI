import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { IntervalPreview } from '@/types/IntervalPreview';
import { MenuItem } from '@/types/Menu';
import { Progress } from '@/types/Progress';

import {
  Card,
  CardDetail,
  CardStudyInfo,
  UserOption,
  ExceptionResponse,
  UserStudyHistory,
  Deck,
  StudyCardForm,
  Paginated,
  UserCard
} from '@/types/schemes';

export const DUMMY_PROGRESS: Progress[] = [
  { value: 10, label: '10', tooltip: 'Completed', color: LEARNING_PROGRESS_BAR_COLORS.completed },
  { value: 20, label: '20', tooltip: 'Learning', color: LEARNING_PROGRESS_BAR_COLORS.learning },
  { value: 30, label: '30', tooltip: 'Reviewing', color: LEARNING_PROGRESS_BAR_COLORS.reviewing }
];

export const DUMMY_RATING_PREVIEW: IntervalPreview = {
  again: new Date(Date.now() + 1 * 60 * 1000),
  hard: new Date(Date.now() + 10 * 60 * 1000),
  good: new Date(Date.now() + 1 * 24 * 60 * 60 * 1000),
  easy: new Date(Date.now() + 1 * 30 * 24 * 60 * 60 * 1000)
};

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
  { code: 'ko', label: '한국어' }, // 한국어
  { code: 'mn', label: 'Монгол' }, // 몽골어
  { code: 'ru', label: 'Русский' }, // 러시아어
  { code: 'th', label: 'ไทย' }, // 태국어
  { code: 'vi', label: 'Tiếng Việt' }, // 베트남어
  { code: 'zh', label: '中文' } // 중국어
];

// Dummy data for api mocking
export const DUMMY_CARD: Card = {
  level: 'easy',
  cardId: 1,
  koreanWord: '사랑',
  languageCode: 'en',
  foreignWord: 'love'
};

export const DUMMY_CARD_DETAIL: CardDetail = {
  cardId: 1,
  koreanWord: '가깝다',
  foreignWord: 'near; close; adjacent',
  level: 'easy',
  languageCode: 'ko',
  originalLanguage: '家具',
  homographNumber: 1,
  partsOfSpeech: '(adj.)',
  pronunciation: '가깝따',
  relatedWords: '반댓말 멀다2 반댓말 멀다2, 멀다2',
  inflection: '가까운, 가꾸어(가꿔), 가까우니, 가깝습니다',
  exampleUsage:
    '<구> 안녕하세요\n<구> 안녕하세요\n<구> 안녕하세요\n<문> 안녕하세요\n<문> 안녕하세요\n<문> 안녕하세요\n<대화> 안녕하세요\n<대화> 안녕하세요\n<대화> 안녕하세요'
};

export const DUMMY_CARDS: Paginated<Card> = {
  size: 3,
  pageSize: 1,
  page: 1,
  content: [DUMMY_CARD, DUMMY_CARD, DUMMY_CARD]
};

export const DUMMY_CARD_STUDY_INFO: CardStudyInfo = {
  lapses: 0,
  reps: 0,
  scheduledDays: 0,
  cardId: 1,
  lastReview: '2025-01-01',
  nextStudyDate: '2025-01-01',
  state: 'new',
  stability: 0
};

export const DUMMY_STUDY_CARD_FORM: StudyCardForm = {
  lapses: 0,
  reps: 0,
  due: new Date(),
  scheduledDays: 0,
  lastReview: '2025-01-01',
  state: 'new',
  stability: 0,
  difficulty: 0
};

export const DUMMY_USER_CARD: UserCard = {
  ...DUMMY_CARD,
  ...DUMMY_STUDY_CARD_FORM,
  ...DUMMY_CARD_DETAIL
};

export const DUMMY_USER_CARDS: Paginated<UserCard> = {
  size: 10,
  pageSize: 1,
  page: 1,
  content: [
    { ...DUMMY_USER_CARD, koreanWord: '하나', foreignWord: 'one', cardId: 1, state: 'overdue' },
    { ...DUMMY_USER_CARD, koreanWord: '둘', foreignWord: 'two', cardId: 2, state: 'overdue' },
    { ...DUMMY_USER_CARD, koreanWord: '셋', foreignWord: 'three', cardId: 3, state: 'overdue' },
    { ...DUMMY_USER_CARD, koreanWord: '넷', foreignWord: 'four', cardId: 4, state: 'overdue' },
    { ...DUMMY_USER_CARD, koreanWord: '다섯', foreignWord: 'five', cardId: 5, state: 'overdue' },
    { ...DUMMY_USER_CARD, koreanWord: '여섯', foreignWord: 'six', cardId: 6, state: 'new' },
    { ...DUMMY_USER_CARD, koreanWord: '일곱', foreignWord: 'seven', cardId: 7, state: 'new' },
    { ...DUMMY_USER_CARD, koreanWord: '여덟', foreignWord: 'eight', cardId: 8, state: 'new' },
    { ...DUMMY_USER_CARD, koreanWord: '아홉', foreignWord: 'nine', cardId: 9, state: 'new' },
    { ...DUMMY_USER_CARD, koreanWord: '열', foreignWord: 'ten', cardId: 10, state: 'new' }
  ]
};

export const DUMMY_DECK: Deck = {
  overdueRate: 0.1,
  cardCounts: 100,
  overdueCounts: 10,
  maturityRate: 0.1,
  maturityCounts: 10,
  category: 'category'
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
  deckType: 'deckType',
  studyType: 'review',
  deckName: 'deckName',
  studyDate: '2025-01-01'
};

export const DUMMY_USER_STUDY_HISTORIES: Paginated<UserStudyHistory> = {
  size: 2,
  pageSize: 1,
  page: 1,
  content: [DUMMY_USER_STUDY_HISTORY, DUMMY_USER_STUDY_HISTORY]
};

export const DUMMY_USER_OPTION: UserOption = {
  todayReviewWords: 0,
  todayStudyWords: 0,
  id: 1,
  languageCode: 'en'
};

export const DUMMY_EXCEPTION_RESPONSE: ExceptionResponse = {
  code: 400,
  message: 'Bad Request'
};
