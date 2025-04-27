'use server';

import {
  Paginated,
  StudyType,
  CardStudyInfo,
  StudyCardForm,
  UserCardServerResponse,
  convertUserCardServerResponseToUserCard
} from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Category, token: string) => {
  const queryStudyType = studyType === 'new' ? 'study' : 'review';

  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';

  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;

  console.log(url);
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data: Paginated<UserCardServerResponse> = await response.json();

  if (response.ok) {
    const convertedData = data.content.map((card) => convertUserCardServerResponseToUserCard(card));
    return {
      ...data,
      content: convertedData
    };
  } else {
    console.log(data);
    if (response.status === 400) {
      throw new Error('SETUP_REQUIRED');
    } else if (response.status === 401) {
      throw new Error('UNAUTHORIZED');
    } else if (response.status === 404) {
      throw new Error('NOT_FOUND');
    } else {
      throw new Error('Failed to fetch user cards');
    }
  }
};

export const getCardStudyInfo = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    throw new Error('Failed to fetch card study info');
  }
};

export const postCardStudyInfo = async (
  cardId: number,
  studyCardForm: StudyCardForm,
  token: string
) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(studyCardForm)
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    throw new Error('Failed to post card study info');
  }
};
