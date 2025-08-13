export type CategoryType = 'difficulty' | 'meaning';

export type Difficulty = 'easy' | 'normal' | 'hard';

export const difficultiesInDisplayOrder: Difficulty[] = ['easy', 'normal', 'hard'];

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

export type Category = Meaning | Difficulty;

export const meaningInDisplayOrder: Meaning[] = [
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

export const getCategoryType = (query: CategoryType | string): string | null => {
  if (difficultiesInDisplayOrder.includes(query as Difficulty)) return 'LEVEL';
  if (meaningInDisplayOrder.includes(query.toUpperCase() as Meaning)) return 'TOPIC';
  return null;
};
