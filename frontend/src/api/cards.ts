'use server';

import { Locale } from '../types/Locale';
import { CardDetail, Card, Paginated } from '../types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_URL;

export const forignSearch = async (locale: Locale, query: string, token: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/foreign-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });

  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Card>;
  } else {
    throw new Error(data.message);
  }
};

export const koreanSearch = async (locale: Locale, query: string, token: string) => {
  const page = 1;
  const pageSize = 10;
  const url = `${endpoint}/cards/korean-search?code=${locale}&query=${query}&page=${page}&pageSize=${pageSize}`;

  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });

  const data = await response.json();

  if (response.ok) {
    return data as Paginated<Card>;
  } else {
    throw new Error(data.message);
  }
};

export const getCard = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}`;

  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });

  const data = await response.json();

  if (response.ok) {
    return data as Card;
  } else {
    throw new Error(data.message);
  }
};

export const getCardDetail = async (cardId: number, token: string) => {
  const url = `${endpoint}/cards/${cardId}/details`;
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as CardDetail;
  } else {
    throw new Error(data.message);
  }
};
