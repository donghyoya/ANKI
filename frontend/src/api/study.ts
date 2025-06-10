'use server';

import { Paginated, StudyType, StudyInfo, StudyInfoDTO, UserCardDTO } from '@/types/schemes';
import { Category } from '@/types/Category';
import { requestApi } from './utils';
import { normalizeQuery, toStudyInfo, toStudyInfoDTO, toUserCard } from '@/utils/converter';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Category, token: string) => {
  const url = `${endpoint}/cards/study?studyType=${normalizeQuery(studyType)}&queryType=${normalizeQuery(query)}&query=${query}`;
  console.log('LOGGING: url', url);

  const response = await requestApi<Paginated<UserCardDTO>>({ url, token });

  const convertedData = response.content.map((card) => toUserCard(card));

  return {
    ...response,
    content: convertedData
  };
};

export const getCardStudyInfo = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const data = await requestApi<StudyInfoDTO>({ url, token });
  const convertedData = toStudyInfo(data);
  return convertedData;
};

export const postCardStudyInfo = async (cardId: number, studyInfo: StudyInfo, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const data = await requestApi<StudyInfoDTO>({
    url,
    token,
    method: 'POST',
    body: toStudyInfoDTO(studyInfo)
  });
  const convertedData = toStudyInfo(data);
  return convertedData;
};
