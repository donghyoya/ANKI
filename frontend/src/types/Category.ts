export type Difficulty = 'EASY' | 'NORMAL' | 'HARD';

export const difficultiesInDisplayOrder: Difficulty[] = ['EASY', 'NORMAL', 'HARD'];

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

export const meaningsInDisplayOrder: Meaning[] = [
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

export const getCategoryType = (query: Difficulty | Meaning | string) => {
  return difficultiesInDisplayOrder.includes(query as Difficulty) ? 'difficulty' : 'meaning';
};
