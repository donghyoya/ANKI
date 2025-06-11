'use server';

import { Locale } from '../types/Locale';
import { KoreanCardDetail, Card, Paginated } from '../types/schemes';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const httpClient = ServerServiceFactory.getHttpClient();

export const foreignSearch = async (locale: Locale, query: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `/cards/foreign-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await httpClient.get<Paginated<Card>>(url);
  return response.data;
};

export const koreanSearch = async (locale: Locale, query: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `/cards/korean-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await httpClient.get<Paginated<Card>>(url);
  return response.data;
};

export const getCard = async (cardId: number) => {
  const url = `/cards/${cardId}`;

  const response = await httpClient.get<Card>(url);
  return response.data;
};

export const getKoreanCardDetail = async (cardId: number) => {
  const url = `/cards/${cardId}/details`;
  const response = await httpClient.get<KoreanCardDetail>(url);
  return response.data;
};
