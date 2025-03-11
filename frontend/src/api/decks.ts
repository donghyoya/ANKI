'use server';

import {
  Deck,
  ExceptionResponse,
  Paginated,
  Level,
  Meaning,
  isLevel,
  Card,
  UserStudyHistory
} from '@/types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_URL;

const requestOptions: RequestInit = {
  headers: {
    accept: 'application/json;charset=UTF-8',
    'Content-Type': 'application/json'
  },
  credentials: 'include',
  cache: 'no-store'
};

export const getDecks = async (queryType: 'level' | 'meaning') => {
  const url = `${endpoint}/decks?queryType=${queryType}`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Deck>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getCardsFromDeck = async (query: Level | Meaning | string) => {
  const queryType = isLevel(query) ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?queryType=${queryType}&query=${query}`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Card>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getUserStudyHistories = async () => {
  const url = `${endpoint}/decks/history`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<UserStudyHistory>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getLatestUserStudyHistory = async () => {
  const url = `${endpoint}/decks/latest`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as UserStudyHistory;
  } else {
    return data as ExceptionResponse;
  }
};
