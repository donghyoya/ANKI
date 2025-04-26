import { Card as FSRSCardSnakeCase, fsrs, generatorParameters } from 'ts-fsrs';
import { FSRSCard } from '@/types/FSRS';
import { convertToSnakeCase } from './converter';

export function createFSRS() {
  const f = fsrs(generatorParameters());
  return {
    ...f,
    repeat: (card: FSRSCard, now: Date) => {
      return f.repeat(convertToSnakeCase(card) as FSRSCardSnakeCase, now);
    }
  };
}
