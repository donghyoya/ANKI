import { State } from 'ts-fsrs';

export const STATE_MAP = {
  [State.New]: 'New',
  [State.Review]: 'Review',
  [State.Learning]: 'Learning',
  [State.Relearning]: 'Relearning'
} as const;

export const STATE_MAP_REVERSE = {
  New: State.New,
  Learning: State.Learning,
  Review: State.Review,
  Relearning: State.Relearning
} as const;