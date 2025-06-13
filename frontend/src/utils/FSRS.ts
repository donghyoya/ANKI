import { Card as FSRSCardSnakeCase, fsrs, generatorParameters } from 'ts-fsrs';
import { StudyInfo } from '@/types/schemes';
import { convertToSnakeCase } from './converter';

export function createFSRS() {
  const f = fsrs(generatorParameters());
  return {
    ...f,
    repeat: (card: StudyInfo, now: Date) => {
      return f.repeat(convertToSnakeCase(card) as FSRSCardSnakeCase, now);
    }
  };
}
