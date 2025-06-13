import { Card as FSRSCardSnakeCase, fsrs, generatorParameters } from 'ts-fsrs';
import { StudyInfo } from '@/types/schemes';
import { convertToCamelCaseDeep, convertToSnakeCase } from './converter';

export function createFSRS() {
  const f = fsrs(generatorParameters());
  return {
    ...f,
    repeat: (card: StudyInfo, now: Date) => {
      const snakeCaseCard = convertToSnakeCase(card) as FSRSCardSnakeCase;
      const iPreview = f.repeat(snakeCaseCard, now);
      const convertedIPreview = convertToCamelCaseDeep(iPreview);
      return convertedIPreview;
    }
  };
}
