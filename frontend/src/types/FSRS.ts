import { Card as FSRSCardSnakeCase } from 'ts-fsrs';
import { SnakeToCamelCase } from './utils';

// ts-fsrs에서는 파라미터가 snake_case로 전달되고 반환되기 때문에 타입 변환
export type FSRSCard = {
  [K in keyof FSRSCardSnakeCase as SnakeToCamelCase<K>]: FSRSCardSnakeCase[K];
};
