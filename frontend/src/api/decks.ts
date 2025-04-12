'use server';

import {
  ExceptionResponse,
  Paginated,
  Level,
  Meaning,
  isLevel,
  CardDetail,
  UserStudyHistory
} from '@/types/schemes';

// TODO
const locale = 'en';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getDecks = async (queryType: 'level' | 'meaning', token: string) => {
  try {
    const url = `${endpoint}/decks?queryType=${queryType}`;
    console.log('요청 URL:', url);
    console.log('요청 토큰:', token);
    const response = await fetch(url, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  } catch (error) {
    console.error('getDecks:', error);
    throw error;
  }
};

export const getCardsFromDeck = async (query: Level | Meaning | string, token: string) => {
  const queryType = isLevel(query) ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${query}`;
  console.log('요청 URL:', url);
  console.log('요청 토큰:', token);
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<CardDetail>;
  } else {
    throw new Error(data.message);
  }
};

export const getUserStudyHistories = async (token: string) => {
  const url = `${endpoint}/decks/history`;
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<UserStudyHistory>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getLatestUserStudyHistory = async (token: string) => {
  const url = `${endpoint}/decks/latest`;
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as UserStudyHistory;
  } else {
    return data as ExceptionResponse;
  }
};
