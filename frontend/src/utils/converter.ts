import { dateDiffInDays, State } from 'ts-fsrs';
import { camelCase, snakeCase } from 'lodash';

import { getCategoryType } from '@/types/Category';
import { UserCard, UserCardServerResponse } from '@/types/schemes';

// 서버에서 받은 데이터를 클라이언트에서 사용할 수 있는 형식으로 변환
export function convertUserCardServerResponseToUserCard(card: UserCardServerResponse): UserCard {
  const {
    level,
    due,
    stability,
    difficulty,
    scheduledDays,
    reps,
    lapses,
    state,
    lastReview,
    ...rest
  } = card;

  const fsrsParameters = {
    due,
    stability,
    difficulty,
    elapsedDays: lastReview ? dateDiffInDays(lastReview, new Date()) : 0,
    scheduledDays,
    reps,
    lapses,
    state: state === 'New' ? State.New : (state as State),
    lastReview
  };

  return { ...rest, difficulty: level, fsrsParameters };
}

// 서버에서 사용하는 명칭으로 변경
export function convertQuery(query: string) {
  switch (query) {
    case 'difficulty':
      return 'level';
    case 'new':
      return 'study';
    default:
      if (getCategoryType(query) === 'meaning') {
        return query.toUpperCase();
      }
      return query;
  }
}

// 객체의 key를 snake_case로 변환
export function convertToSnakeCase<T extends Record<string, unknown>>(obj: T) {
  return Object.entries(obj).reduce((acc, [key, value]) => {
    const snakeKey = snakeCase(key);
    return { ...acc, [snakeKey]: value };
  }, {});
}

// 객체의 key를 camelCase로 변환
export function convertToCamelCase<T extends Record<string, unknown>>(obj: T) {
  return Object.entries(obj).reduce((acc, [key, value]) => {
    const camelKey = camelCase(key);
    return { ...acc, [camelKey]: value };
  }, {});
}
