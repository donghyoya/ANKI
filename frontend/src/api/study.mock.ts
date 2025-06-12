/* eslint-disable @typescript-eslint/no-unused-vars */

import { toStudyInfo } from '@/utils/converter';
import { DUMMY_STUDY_INFO, DUMMY_STUDY_INFO_DTO, DUMMY_USER_CARDS } from '@/utils/dummyData';
import { Paginated, StudyInfo, StudyType, UserCard } from '@/types/schemes';
import { Category } from '@/types/Category';

export const getLearningCards = async (studyType: StudyType, query: Category) => {
  return new Promise<Paginated<UserCard>>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_CARDS;
      console.log('mockGetUserCards:', data);
      resolve(data);
    }, 500);
  });
};

export const getStudyInfo = async (cardId: number) => {
  return new Promise<StudyInfo>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_STUDY_INFO;
      console.log('mockGetCardStudyInfo:', data);
      resolve(data);
    }, 500);
  });
};

export const postStudyInfo = async (cardId: number, userCardId: number, studyInfo: StudyInfo) => {
  return new Promise<StudyInfo>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_STUDY_INFO_DTO;
      const convertedData = toStudyInfo(data);
      console.log('mockPostCardStudyInfo:', data);
      resolve(convertedData);
    }, 500);
  });
};
