import { Locale } from './Locale';

export type CardLevel = 'easy' | 'normal' | 'hard';

export type StudyType = 'review' | 'new';

export type Level = 'easy' | 'normal' | 'hard';

export const allLevels: Level[] = ['easy', 'normal', 'hard'];

export type Meaning =
  | 'CONCEPT'
  | 'ECONOMY'
  | 'SCIENCE'
  | 'TRANSPORT'
  | 'WEATHER'
  | 'NEWS'
  | 'FEELING'
  | 'GRAMMAR_AND_LANGUAGE'
  | 'CULTURE'
  | 'HOSPITAL'
  | 'LIFE'
  | 'LIVING'
  | 'PERSONALITY'
  | 'NUMBER'
  | 'COMMUNICATION'
  | 'TIME'
  | 'FOOD'
  | 'RELATIONSHIPS'
  | 'NATURE'
  | 'POLITICS'
  | 'RELIGION'
  | 'WORK'
  | 'HOME'
  | 'FASHION_AND_APPEARANCE'
  | 'SCHOOL'
  | 'ACTION'
  | 'ADMINISTRATION';

export const allMeanings: Meaning[] = [
  'CONCEPT',
  'ECONOMY',
  'SCIENCE',
  'TRANSPORT',
  'WEATHER',
  'NEWS',
  'FEELING',
  'GRAMMAR_AND_LANGUAGE',
  'CULTURE',
  'HOSPITAL',
  'LIFE',
  'LIVING',
  'PERSONALITY',
  'NUMBER',
  'COMMUNICATION',
  'TIME',
  'FOOD',
  'RELATIONSHIPS',
  'NATURE',
  'POLITICS',
  'RELIGION',
  'WORK',
  'HOME',
  'FASHION_AND_APPEARANCE',
  'SCHOOL',
  'ACTION',
  'ADMINISTRATION'
];

export type CardCategory = Level | Meaning;

export const allCardCategories: CardCategory[] = [...allLevels, ...allMeanings];

export type Paginated<T> = {
  size: number;
  pageSize: number;
  page: number;
  content: T[];
};

export interface Card {
  level: CardLevel;
  cardId: number;
  koreanWord: string;
  languageCode: Locale;
  foreignWord: string;
}

export interface CardStudyInfo {
  lapses: number;
  reps: number;
  scheduledDays: number;
  cardId: number;
  lastReview: string;
  nextStudyDate: string;
  state: string;
  stability: number;
}

export interface UserCard extends Card {
  lapses: number;
  reps: number;
  due: Date;
  difficulty: number;
  scheduledDays: number;
  lastReview: string;
  state: string;
  stability: number;
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

export interface Deck {
  overdueRate: number;
  cardCounts: number;
  overdueCounts: number;
  maturityRate: number;
  maturityCounts: number;
  category: string;
}

export interface UserStudyHistory {
  deckType: string;
  studyType: StudyType;
  deckName: string;
  studyDate: string;
}

export type UserOption = {
  todayReviewWords: number;
  todayStudyWords: number;
  id: number;
  languageCode: Locale;
};

export type ExceptionResponse = {
  code: number;
  message: string;
};

export const isLevel = (query: Level | Meaning | string): query is Level => {
  return allLevels.includes(query as Level);
};
