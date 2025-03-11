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

const endpoint = process.env.NEXT_PUBLIC_API_URL;

const requestOptions: RequestInit = {
  headers: {
    accept: 'application/json;charset=UTF-8',
    'Content-Type': 'application/json'
  },
  credentials: 'include',
  cache: 'no-store'
};

export const getUserCards = async (studyType: StudyType, query: Level | Meaning) => {
  const queryStudyType = studyType === 'new' ? 'study' : 'review';
  const queryType = isLevel(query) ? 'level' : 'meaning';
  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${query}`;
  console.log('url:', url);
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<UserCard>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getCardStudyInfo = async (cardId: number) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    return data as ExceptionResponse;
  }
};

export const postCardStudyInfo = async (cardId: number, studyCardForm: StudyCardForm) => {
  const url = `${endpoint}/cards/${cardId}/study`;
  const response = await fetch(url, {
    ...requestOptions,
    method: 'POST',
    body: JSON.stringify(studyCardForm)
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    return data as ExceptionResponse;
  }
};
