'use server';

import { Locale } from '../types/Locale';
import { CardDetail, Card, ExceptionResponse, Paginated } from '../types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_ENDPOINT;

// TODO
const locale: Locale = 'en';

const requestOptions: RequestInit = {
  headers: {
    accept: 'application/json;charset=UTF-8',
    'Content-Type': 'application/json'
  },
  credentials: 'include',
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
