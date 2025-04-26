import { FSRSCard } from './FSRS';
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

export interface CardStudyInfo extends FSRSCard {
  cardId: number;
  nextStudyDate: string;
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
  fsrsParameters: FSRSCard;
}

// 서버에서는 difficulty대신 level 명칭 사용
export interface UserCardServerResponse
  extends Omit<CardDetail, 'difficulty'>,
    Omit<FSRSCard, 'state'> {
  level: Difficulty;
  userCardId: number;
  state: string | number;
}

export interface Deck {
  cardCounts: number;
  overdueCounts: number;
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
