'use server';

import {
  ExceptionResponse,
  UserCard,
  Paginated,
  Level,
  Meaning,
  StudyType,
  CardStudyInfo,
  StudyCardForm
} from '@/types/schemes';

import { isLevel } from '@/types/schemes';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Level | Meaning, token: string) => {
  const queryStudyType = studyType === 'new' ? 'study' : 'review';
  const queryType = isLevel(query) ? 'level' : 'meaning';
  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${query}`;
  console.log('getUserCards url:', url);
  console.log('getUserCards token:', token);
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<UserCard>;
  } else {
    return data as ExceptionResponse;
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
    return data as ExceptionResponse;
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
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    return data as ExceptionResponse;
  }
};
