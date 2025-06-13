'use server';

import { Locale } from '@/types/Locale';
import { Paginated, UserStudyHistory, Deck, KoreanCardWithForeignWords } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { requestApi, tryRefresh } from './utils';
import { normalizeQuery } from '@/utils/converter';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getDecks = async (queryType: 'difficulty' | 'meaning', token: string) => {
  const accessToken = await tryRefresh(token);
  const url = `${endpoint}/decks?queryType=${normalizeQuery(queryType)}`;
  const response = await requestApi<Paginated<Deck>>({ url, token: accessToken ?? '' });
  return response;
};

export const getCardsFromDeck = async (locale: Locale, query: Category) => {
  const queryType = normalizeQuery(getCategoryType(query));
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${query}`;
  const response = await requestApi<Paginated<KoreanCardWithForeignWords>>({ url });
  return response;
};

export const getUserStudyHistories = async (token: string) => {
  const url = `${endpoint}/decks/history`;
  const response = await requestApi<Paginated<UserStudyHistory>>({ url, token });
  return response;
};

export const getLatestUserStudyHistory = async (token: string) => {
  const url = `${endpoint}/decks/latest`;
  const response = await requestApi<UserStudyHistory>({ url, token });
  return response;
};
