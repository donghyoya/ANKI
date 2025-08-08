'use server';

import { Locale } from '@/types/Locale';
import { Paginated, UserStudyHistory, Deck, KoreanCardWithForeignWords } from '@/types/schemes';
import { Category, CategoryType, getCategoryType } from '@/types/Category';
import { normalizeQuery } from '@/utils/converter';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const httpClient = ServerServiceFactory.getHttpClient();

export const getDecks = async (categoryType: CategoryType) => {
  const url = `/decks?queryType=${normalizeQuery(categoryType)}`;
  const response = await httpClient.get<Paginated<Deck>>(url);
  return response.data;
};

export const getCardsFromDeck = async (locale: Locale, category: Category, page: number) => {
  const categoryType = getCategoryType(category);
  if (!categoryType) throw new Error('Invalid category type');
  const url = `/decks/cards?code=${locale}&queryType=${normalizeQuery(categoryType)}&query=${normalizeQuery(category)}&page=${page}&pageSize=100`;
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
