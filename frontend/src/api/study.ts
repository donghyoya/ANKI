'use server';

import { UserCard, Paginated, StudyType, CardStudyInfo, StudyCardForm } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserCards = async (studyType: StudyType, query: Category, token: string) => {
  const queryStudyType = studyType === 'new' ? 'study' : 'review';
  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : query;
  const url = `${endpoint}/cards/study?studyType=${queryStudyType}&queryType=${queryType}&query=${query}`;
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
    throw new Error('Failed to fetch user cards');
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
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardStudyInfo;
  } else {
    throw new Error('Failed to post card study info');
  }
};
