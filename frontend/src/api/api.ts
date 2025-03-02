'use server';

import { Locale } from '../types/Locale';
import {
  CardDetail,
  Card,
  CardStudyInfo,
  ExceptionResponse,
  Level,
  Meaning,
  StudyCardForm,
  StudyType,
  UserOption,
  UserStudyHistory,
  Paginated,
  Deck,
  UserCard
} from '../types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_ENDPOINT;

const locale: Locale = 'en';

const requestOptions: RequestInit = {
  headers: {
    accept: 'application/json;charset=UTF-8'
  },
  cache: 'no-store'
};

export const forignSearch = async (query: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/foreign-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;
  console.log(url);

  const response = await fetch(url, requestOptions);

  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Card>;
  } else {
    return data as ExceptionResponse;
  }
};

export const koreanSearch = async (query: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/korean-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Card>;
  } else {
    return data as ExceptionResponse;
  }
};

export const getCard = async (cardId: number) => {
  const url = `${endpoint}/cards/${cardId}`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as Card;
  } else {
    return data as ExceptionResponse;
  }
};

export const getCardDetail = async (cardId: number) => {
  const url = `${endpoint}/cards/${cardId}/details`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as CardDetail;
  } else {
    return data as ExceptionResponse;
  }
};

export const getUserCards = async (
  studyType: StudyType,
  queryType: 'level' | 'meaning',
  query: Level | Meaning
) => {
  const url = `${endpoint}/cards/study?studyType=${studyType}&queryType=${queryType}&query=${query}`;
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

export const getCardsFromDeck = async (queryType: 'level' | 'meaning', query: Level | Meaning) => {
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

export const getUserOption = async () => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};

export const postUserOption = async (userOption: UserOption) => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, {
    ...requestOptions,
    method: 'POST',
    body: JSON.stringify(userOption)
  });
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};
