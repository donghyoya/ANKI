import {
  Paginated,
  Deck,
  Card,
  CardDetail,
  CardStudyInfo,
  UserStudyHistory,
  UserOption,
  ExceptionResponse,
  UserCard
} from '@/types/schemes';

import {
  DUMMY_CARD,
  DUMMY_CARDS,
  DUMMY_DECKS,
  DUMMY_STUDY_CARD_FORM,
  DUMMY_USER_CARDS,
  DUMMY_USER_OPTION,
  DUMMY_USER_STUDY_HISTORIES,
  DUMMY_USER_STUDY_HISTORY,
  DUMMY_CARD_DETAIL,
  DUMMY_CARD_STUDY_INFO
} from '@/utils/dummyData';

export const mockForeignSearch = async () => {
  return new Promise<Paginated<Card> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARDS;
      console.log('mockForeignSearch:', data);
      resolve(data);
    }, 500);
  });
};

export const mockKoreanSearch = async () => {
  return new Promise<Paginated<Card> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARDS;
      console.log('mockKoreanSearch:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetCard = async () => {
  return new Promise<Card | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARD;
      console.log('mockGetCard:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetCardDetail = async () => {
  return new Promise<CardDetail | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARD_DETAIL;
      console.log('mockGetCardDetail:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetUserCards = async () => {
  return new Promise<Paginated<UserCard> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_CARDS;
      console.log('mockGetUserCards:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetCardStudyInfo = async () => {
  return new Promise<CardStudyInfo | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARD_STUDY_INFO;
      console.log('mockGetCardStudyInfo:', data);
      resolve(data);
    }, 500);
  });
};

export const mockPostCardStudyInfo = async () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const data = DUMMY_STUDY_CARD_FORM;
      console.log('mockPostCardStudyInfo:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetDecks = async (queryType: 'level' | 'meaning') => {
  return new Promise<Paginated<Deck> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = queryType === 'level' ? DUMMY_DECKS : DUMMY_DECKS;
      console.log('mockGetDecks:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetCardsFromDeck = async () => {
  return new Promise<Paginated<Card> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_CARDS;
      console.log('mockGetCardsFromDeck:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetUserStudyHistories = async () => {
  return new Promise<Paginated<UserStudyHistory> | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_STUDY_HISTORIES;
      console.log('mockGetUserStudyHistories:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetLatestUserStudyHistory = async () => {
  return new Promise<UserStudyHistory | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_STUDY_HISTORY;
      console.log('mockGetLatestUserStudyHistory:', data);
      resolve(data);
    }, 500);
  });
};

export const mockGetUserOption = async () => {
  return new Promise<UserOption | ExceptionResponse>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_OPTION;
      console.log('mockGetUserOption:', data);
      resolve(data);
    }, 500);
  });
};

export const mockPostUserOption = async () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_OPTION;
      console.log('mockPostUserOption:', data);
      resolve(data);
    }, 500);
  });
};
