import { Difficulty, Category } from './Category';
import { Locale } from './Locale';
import { SnakeToCamelCase } from './typeTransform';
import { Card as FSRSCard, State } from 'ts-fsrs';

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
  meanings: {
    foreignMeaning: string;
    partsOfSpeech: string;
    pronunciation: string;
    languageCode: Locale;
    originalLanguage: string;
    foreignWord: string;
    relatedWords: string;
    inflection: string;
    exampleUsage: string;
  };
}

export type StudyInfo = {
  [K in keyof FSRSCard as SnakeToCamelCase<K>]: FSRSCard[K];
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
  overdueRate: number;
  cardCounts: number;
  overdueCounts: number;
  maturityRate: number;
  maturityCounts: number;
  category: Category;
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
  utcOffset: number | null;
  languageCode: Locale;
};

export function convertUserCardDTOToUserCard(card: UserCardDTO): UserCard {
  const { studyInfo, ...rest } = card;
  const { lapses, reps, due, scheduledDays, stability, difficulty, state } = studyInfo;
  const newStudyInfo = {
    lapses,
    reps,
    due,
    scheduledDays,
    lastReview: studyInfo.lastReview ? new Date(studyInfo.lastReview) : undefined,
    stability,
    difficulty,
    state: state === 'New' ? State.New : state === 'Learning' ? State.Learning : State.Review,
    elapsedDays: 0,
    learningSteps: 0
  } as StudyInfo;

  return { ...rest, studyInfo: newStudyInfo };
}
