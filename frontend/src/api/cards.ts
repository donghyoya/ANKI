'use server';

import { Locale } from '../types/Locale';
import { CardDetail, Card, Paginated } from '../types/schemes';
import { requestApi } from './utils';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const forignSearch = async (locale: Locale, query: string, token: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/foreign-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await requestApi<Paginated<Card>>({ url, token });
  return response;
};

export const koreanSearch = async (locale: Locale, query: string, token: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/korean-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await requestApi<Paginated<Card>>({ url, token });
  return response;
};

export const getCard = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}`;

  const response = await requestApi<Card>({ url, token });
  return response;
};

export const getCardDetail = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/details`;
  const response = await requestApi<CardDetail>({ url, token });
  return response;
};
