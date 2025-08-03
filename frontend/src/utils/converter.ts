import { dateDiffInDays } from 'ts-fsrs';

import { KoreanCardDetail, StudyInfo, StudyInfoDTO, UserCard, UserCardDTO } from '@/types/schemes';
import { DUMMY_KOR_CARD_DETAIL } from './dummyData';
import { getCategoryType } from '@/types/Category';
import { STATE_MAP, STATE_MAP_REVERSE } from '@/constants/study';

// 서버에서 받은 데이터를 클라이언트에서 사용할 수 있는 형식으로 변환
export function toUserCard(card: UserCardDTO): UserCard {
  const { studyInfo, koreanCard, userCardId } = card;
  const { due, stability, difficulty, scheduledDays, reps, lapses, state, lastReview } = studyInfo;

  const newStudyInfo = {
    due: new Date(due),
    stability,
    difficulty,
    elapsedDays: lastReview ? dateDiffInDays(new Date(lastReview), new Date()) : 0,
    scheduledDays,
    reps,
    lapses,
    learningSteps: 0,
    state: STATE_MAP_REVERSE[state],
    lastReview: lastReview ? new Date(lastReview) : undefined
  };

  return { koreanCard, userCardId, studyInfo: newStudyInfo };
}

export function toStudyInfo(studyInfo: StudyInfoDTO): StudyInfo {
  return {
    ...studyInfo,
    due: new Date(studyInfo.due),
    state: STATE_MAP_REVERSE[studyInfo.state],
    learningSteps: 0,
    elapsedDays: studyInfo.lastReview
      ? dateDiffInDays(new Date(studyInfo.lastReview), new Date())
      : 0,
    lastReview: studyInfo.lastReview ? new Date(studyInfo.lastReview) : undefined
  };
}

export function toStudyInfoDTO(studyInfo: StudyInfo): StudyInfoDTO {
  return {
    ...studyInfo,
    state: STATE_MAP[studyInfo.state],
    lastReview: studyInfo.lastReview ? studyInfo.lastReview.toISOString() : null
  };
}

// API 미구현으로 인해 타입만 맞춰서 반환
export function toKoreanCardDetail(card: UserCardDTO): KoreanCardDetail {
  return {
    ...DUMMY_KOR_CARD_DETAIL,
    ...card
  };
}

// 서버에서 사용하는 명칭으로 변경
export function normalizeQuery(query: string) {
  switch (query) {
    case 'difficulty':
      return 'LEVEL';
    case 'meaning':
      return 'TOPIC';
    case 'new':
      return 'study';
    default:
      if (getCategoryType(query) === 'meaning') {
        return query.toUpperCase();
      }
      return query;
  }
}
