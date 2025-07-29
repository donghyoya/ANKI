import { Difficulty, Category } from './Category';
import { Locale } from './Locale';
import { SnakeToCamelCase } from './typeTransform';
import { Card as FSRSCard } from 'ts-fsrs';

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

export interface KoreanCard {
  homographNumber: number;
  level: Difficulty;
  topics: string[];
  cardId: number;
  koreanWord: string;
}

export interface KoreanCardDetail extends KoreanCard {
  meanings: Array<{
    foreignMeaning: string;
    partsOfSpeech: string;
    pronunciation: string;
    languageCode: Locale;
    originalLanguage: string;
    foreignWord: string;
    relatedWords: string;
    inflection: string;
    exampleUsage: string;
  }>;
}

export interface KoreanCardWithForeignWords extends KoreanCard {
  foreignWords: string[];
}

export type StudyInfo = {
  [K in keyof FSRSCard as SnakeToCamelCase<K>]: FSRSCard[K];
} & {
  lastRating?: Date;
};

export type StudyInfoDTO = Omit<
  StudyInfo,
  'lastReview' | 'state' | 'elapsedDays' | 'learningSteps'
> & {
  lastReview: string | null;
  state: 'New' | 'Learning' | 'Review' | 'Relearning';
};

export interface UserCard {
  koreanCard: KoreanCard;
  studyInfo: StudyInfo;
  userCardId: number;
}

export interface UserCardDTO {
  koreanCard: KoreanCard;
  studyInfo: StudyInfoDTO;
  userCardId: number;
}

export interface Deck {
  category: Category;
  cardCounts: number;
  learningCounts: number;
  newCounts: number;
  overdueCounts: number;
  maturityCounts: number;
}

export interface UserStudyHistory {
  deckType: 'level' | 'meaning';
  studyType: StudyType;
  deckName: Category;
  studyDate: string;
}

export type UserOption = {
  dailyReviewWords: number;
  dailyStudyWords: number;
  utcOffset: number;
  languageCode: Locale;
};

export type TokenDTO = {
  accessToken: string;
  refreshToken: string;
  isSetup: boolean;
};
