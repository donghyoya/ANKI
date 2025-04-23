'use server';

import { Locale } from '@/types/Locale';
import { Paginated, CardDetail, UserStudyHistory } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getDecks = async (queryType: 'difficulty' | 'meaning', token: string) => {
  try {
    const url = `${endpoint}/decks?queryType=${queryType === 'difficulty' ? 'level' : 'meaning'}`;
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

export const getCardsFromDeck = async (locale: Locale, query: Category, token: string) => {
  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;
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
    throw new Error('Failed to fetch user study histories');
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
    throw new Error('Failed to fetch latest user study history');
  }
};
