'use server';

import { Paginated, StudyType, StudyInfo, StudyInfoDTO, UserCardDTO } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { normalizeQuery, toStudyInfo, toUserCard } from '@/utils/converter';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const httpClient = ServerServiceFactory.getHttpClient();

export const getLearningCards = async (studyType: StudyType, category: Category) => {
  const studyTypeQuery = normalizeQuery(studyType);
  const categoryType = getCategoryType(category);
  if (!categoryType) throw new Error('Invalid category type');
  const url = `/cards/study?studyType=${studyTypeQuery}&queryType=${categoryType}&query=${normalizeQuery(category)}`;

  const response = await httpClient.get<Paginated<UserCardDTO>>(url);
  const convertedData = response.data.content.map((card) => toUserCard(card));

  return { ...response.data, content: convertedData };
};

export const getStudyInfo = async (cardId: number) => {
  const url = `/cards/${cardId}/study`;
  const response = await httpClient.get<StudyInfoDTO>(url);
  return toStudyInfo(response.data);
};

export const postStudyInfo = async (userCardId: number, studyInfo: StudyInfo) => {
  const url = `/cards/${userCardId}/study`;
  const response = await httpClient.post<StudyInfoDTO>(url, studyInfo);
  return toStudyInfo(response.data);
};
