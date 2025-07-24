'use server';

import { Locale } from '@/types/Locale';
import { Paginated, UserStudyHistory, Deck, KoreanCardWithForeignWords } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { normalizeQuery } from '@/utils/converter';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const httpClient = ServerServiceFactory.getHttpClient();

export const getDecks = async (queryType: 'difficulty' | 'meaning') => {
  const url = `/decks?queryType=${normalizeQuery(queryType)}`;
  console.log('url', url);
  const response = await httpClient.get<Paginated<Deck>>(url);
  return response.data;
};

export const getCardsFromDeck = async (locale: Locale, query: Category) => {
  const queryType = getCategoryType(query);
  if (!queryType) throw new Error('Invalid query type');
  const url = `/decks/cards?code=${locale}&queryType=${queryType}&query=${normalizeQuery(query)}`;
  const response = await httpClient.get<Paginated<KoreanCardWithForeignWords>>(url);
  return response.data;
};

export const getUserStudyHistories = async () => {
  const url = `/decks/history`;
  const response = await httpClient.get<Paginated<UserStudyHistory>>(url);
  return response.data;
};

export const getLatestUserStudyHistory = async () => {
  const url = `/decks/latest`;
  const response = await httpClient.get<UserStudyHistory>(url);
  return response.data;
};
