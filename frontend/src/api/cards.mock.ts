/* eslint-disable @typescript-eslint/no-unused-vars */

import { Locale } from '@/types/Locale';
import { Card, KoreanCardDetail, Paginated } from '@/types/schemes';
import { DUMMY_CARD, DUMMY_CARDS, DUMMY_KOR_CARD_DETAIL } from '@/utils/dummyData';

export const foreignSearch = async (locale: Locale, query: string) => {
  return new Promise<Paginated<Card>>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARDS;
      console.log('mockForeignSearch:', data);
      resolve(data);
    }, 500);
  });
};

export const koreanSearch = async (locale: Locale, query: string) => {
  return new Promise<Paginated<Card>>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARDS;
      console.log('mockKoreanSearch:', data);
      resolve(data);
    }, 500);
  });
};

export const getCard = async (cardId: number) => {
  return new Promise<Card>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARD;
      console.log('mockGetCard:', data);
      resolve(data);
    }, 500);
  });
};

export const getCardDetail = async (cardId: number) => {
  return new Promise<KoreanCardDetail>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_KOR_CARD_DETAIL;
      console.log('mockGetCardDetail:', data);
      resolve(data);
    }, 500);
  });
};
