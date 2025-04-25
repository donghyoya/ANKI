import { Difficulty, Category } from './Category';
import { Locale } from './Locale';

export type StudyType = 'review' | 'new';

export type Paginated<T> = {
  size: number;
  pageSize: number;
  page: number;
  content: T[];
};

export interface Card {
  difficulty: Difficulty;
  cardId: number;
  koreanWord: string;
  languageCode: Locale;
  foreignWord: string;
}

export interface FSRSParameters {
  lapses: number;
  reps: number;
  due: Date;
  difficulty: number;
  scheduledDays: number;
  lastReview: string;
  state: string;
  stability: number;
}

export interface CardStudyInfo extends FSRSParameters {
  cardId: number;
  nextStudyDate: string;
}

export interface StudyCardForm {
  lapses: number;
  reps: number;
  due: Date;
  scheduledDays: number;
  lastReview: string;
  state: string;
  stability: number;
  difficulty: number;
}

export interface CardDetail extends Card {
  originalLanguage: string;
  homographNumber: number;
  partsOfSpeech: string;
  pronunciation: string;
  relatedWords: string;
  inflection: string;
  exampleUsage: string;
}

export interface UserCard extends CardDetail {
  fsrsParameters: FSRSParameters;
}

export interface UserCardServerResponse extends Omit<CardDetail, 'difficulty'>, FSRSParameters {
  // 서버에서는 difficulty가 아니라 level로 받아옴
  level: Difficulty;
  userCardId: number;
}

export interface Deck {
  overdueRate: number;
  cardCounts: number;
  overdueCounts: number;
  maturityRate: number;
  maturityCounts: number;
  category: Category;
}

export interface UserStudyHistory {
  deckType: string;
  studyType: StudyType;
  deckName: string;
  studyDate: string;
}

export type UserOption = {
  dailyReviewWords: number;
  dailyStudyWords: number;
  utcOffset: number | null;
  languageCode: Locale;
};

export function convertUserCardServerResponseToUserCard(card: UserCardServerResponse): UserCard {
  const {
    lapses,
    reps,
    due,
    scheduledDays,
    lastReview,
    stability,
    difficulty,
    state,
    level,
    ...rest
  } = card;
  const fsrsParameters = {
    lapses,
    reps,
    due,
    scheduledDays,
    lastReview,
    stability,
    difficulty,
    state
  };
  return { ...rest, difficulty: level, fsrsParameters };
}
