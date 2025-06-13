'use server';

import { Paginated, StudyType, StudyInfo, StudyInfoDTO, UserCardDTO } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { requestApi } from './utils';
import { normalizeQuery, toStudyInfo, toStudyInfoDTO, toUserCard } from '@/utils/converter';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getLearningCards = async (studyType: StudyType, query: Category, token: string) => {
  const queryType = normalizeQuery(getCategoryType(query));
  const url = `${endpoint}/cards/study?studyType=${normalizeQuery(studyType)}&queryType=${queryType}&query=${query}`;

  const response = await requestApi<Paginated<UserCardDTO>>({ url, token });

  const convertedData = response.content.map((card) => toUserCard(card));

  return {
    ...response,
    content: convertedData
  };
};

export const getStudyInfo = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const data = await requestApi<StudyInfoDTO>({ url, token });
  const convertedData = toStudyInfo(data);
  return convertedData;
};

export const postStudyInfo = async (cardId: number, studyInfo: StudyInfo, token: string) => {
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
