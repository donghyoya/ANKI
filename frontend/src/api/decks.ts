'use server';

import { Locale } from '@/types/Locale';
import { Paginated, UserCardDTO, UserStudyHistory, Deck } from '@/types/schemes';
import { Category, getCategoryType } from '@/types/Category';
import { requestApi, tryRefresh } from './utils';
import { toKoreanCardDetail, toUserCard } from '@/utils/converter';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getDecks = async (queryType: 'difficulty' | 'meaning', token: string) => {
  const accessToken = await tryRefresh(token);
  const url = `${endpoint}/decks?queryType=${queryType === 'difficulty' ? 'level' : 'meaning'}`;
  const response = await requestApi<Paginated<Deck>>({ url, token: accessToken ?? '' });
  return response;
};

export const getUserCardsFromDeck = async (locale: Locale, query: Category) => {
  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;
  const response = await requestApi<Paginated<UserCardDTO>>({ url });
  const cards = response.content.map((card) => toUserCard(card));
  return { ...response, content: cards };
};

// API 미구현으로 인해 타입만 맞춰서 반환
export const getKoreanCardDetailsFromDeck = async (locale: Locale, query: Category) => {
  const queryType = getCategoryType(query) === 'difficulty' ? 'level' : 'meaning';
  const url = `${endpoint}/decks/cards?code=${locale}&queryType=${queryType}&query=${queryType === 'level' ? query : query.toUpperCase()}`;
  const response = await requestApi<Paginated<UserCardDTO>>({ url });
  const cards = response.content.map((card) => toKoreanCardDetail(card));
  return { ...response, content: cards };
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
