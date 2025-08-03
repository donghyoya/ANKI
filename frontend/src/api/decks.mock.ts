/* eslint-disable @typescript-eslint/no-unused-vars */

import { Locale } from '@/types/Locale';
import { Category, CategoryType } from '@/types/Category';
import { Paginated } from '@/types/schemes';
import { Deck, KoreanCardWithForeignWords, UserStudyHistory } from '@/types/schemes';
import {
  DUMMY_DECKS,
  DUMMY_KOR_CARD_WITH_FOREIGN_WORDS,
  DUMMY_USER_STUDY_HISTORIES,
  DUMMY_USER_STUDY_HISTORY
} from '@/utils/dummyData';

export const getDecks = async (categoryType: CategoryType) => {
  return new Promise<Paginated<Deck>>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_DECKS;
      console.log('mockGetDecks:', data);
      resolve(data);
    }, 500);
  });
};

export const getCardsFromDeck = async (locale: Locale, query: Category) => {
  return new Promise<Paginated<KoreanCardWithForeignWords>>((resolve) => {
    setTimeout(() => {
      const data = {
        content: [DUMMY_KOR_CARD_WITH_FOREIGN_WORDS],
        page: 1,
        pageSize: 10,
        size: 1
      };
      console.log('mockGetCardsFromDeck:', data);
      resolve(data);
    }, 500);
  });
};

export const getUserStudyHistories = async () => {
  return new Promise<Paginated<UserStudyHistory>>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_STUDY_HISTORIES;
      console.log('mockGetUserStudyHistories:', data);
      resolve(data);
    }, 500);
  });
};

export const getLatestUserStudyHistory = async () => {
  return new Promise<UserStudyHistory>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_STUDY_HISTORY;
      console.log('mockGetLatestUserStudyHistory:', data);
      resolve(data);
    }, 500);
  });
};
