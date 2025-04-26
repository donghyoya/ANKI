'use server';

import { StudyType, CardStudyInfo, UserCardServerResponse } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { FSRSCard } from '@/types/FSRS';
import { convertUserCardServerResponseToUserCard, convertQuery } from '@/utils/converter';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Category, token: string) => {
  const queryStudyType = convertQuery(studyType);
  const queryType = convertQuery(getCategoryType(query));
  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${convertQuery(query)}`;

  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    const convertedData = data.content.map((card: UserCardServerResponse) =>
      convertUserCardServerResponseToUserCard(card)
    );
    return {
      ...data,
      content: convertedData
    };
  } else {
    console.log(data);
    if (response.status === 400) {
      if (data.message === 'query parameter is invalid') {
        throw new Error('INVALID_QUERY_PARAMETER');
      } else {
        throw new Error('SETUP_REQUIRED');
      }
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

export const postCardStudyInfo = async (cardId: number, studyCardForm: FSRSCard, token: string) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    throw new Error('Failed to post card study info');
  }
};
