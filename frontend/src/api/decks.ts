'use server';

import { Locale } from '@/types/Locale';
import { Paginated, CardDetail, UserStudyHistory, Deck } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { apiRequest } from './utils';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getDecks = async (queryType: 'difficulty' | 'meaning', token: string) => {
  const url = `${endpoint}/decks?queryType=${queryType === 'difficulty' ? 'level' : 'meaning'}`;
  const response = await apiRequest<Paginated<Deck>>({ url, token });
  return response;
};

export const getCardsFromDeck = async (locale: Locale, query: Category) => {
  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;

  const response = await apiRequest<Paginated<CardDetail>>({ url });
  return response;
};

export const getUserStudyHistories = async (token: string) => {
  const url = `${endpoint}/decks/history`;
  const response = await apiRequest<Paginated<UserStudyHistory>>({ url, token });
  return response;
};

export const getLatestUserStudyHistory = async (token: string) => {
  const url = `${endpoint}/decks/latest`;
  const response = await apiRequest<UserStudyHistory>({ url, token });
  return response;
};
