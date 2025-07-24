import { Grade, Rating, State } from 'ts-fsrs';

export interface Card {
  due: Date;
  stability: number;
  difficulty: number;
  elapsedDays: number;
  scheduledDays: number;
  learningSteps: number;
  reps: number;
  lapses: number;
  state: State;
  lastReview?: Date;
}

export interface ReviewLog {
  rating: Rating;
  state: State;
  due: Date;
  stability: number;
  difficulty: number;
  elapsedDays: number;
  lastElapsedDays: number;
  scheduledDays: number;
  learningSteps: number;
  review: Date;
}

export type RecordLogItem = {
  card: Card;
  log: ReviewLog;
};

export type RecordLog = {
  [key in Grade]: RecordLogItem;
};

export interface IPreview extends RecordLog {
  [Symbol.iterator](): IterableIterator<RecordLogItem>;
}
