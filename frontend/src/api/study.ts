'use server';

import {
  Paginated,
  StudyType,
  StudyInfo,
  StudyInfoDTO,
  UserCardDTO,
  convertUserCardDTOToUserCard
} from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { requestApi } from './utils';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Category, token: string) => {
  const queryStudyType = studyType === 'new' ? 'study' : 'review';

  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';

  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;

  const response = await requestApi<Paginated<UserCardDTO>>({ url, token });

  const convertedData = response.content.map((card) => convertUserCardDTOToUserCard(card));

  return {
    ...response,
    content: convertedData
  };
};

export const getCardStudyInfo = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await requestApi<StudyInfo>({ url, token });
  return response;
};

export const postCardStudyInfo = async (cardId: number, studyInfo: StudyInfoDTO, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await requestApi<StudyInfo>({
    url,
    token,
    method: 'POST',
    body: studyInfo
  });
  return response;
};
