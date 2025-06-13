import { useAppSelector } from '@/store/hooks';
import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import { KoreanCardDetail, UserCard } from '@/types/schemes';
import { Rating } from '@/types/IntervalPreview';
import { Category } from '@/types/Category';

import { getLearningCards } from '@/api/study';
import { RootState } from '@/store';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import { State } from 'ts-fsrs';
import { getCardDetail } from '@/api/cards';
import { useLocale } from 'next-intl';
import { Locale } from '@/types/Locale';

export const useStudyQueue = (category: Category) => {
  const initialStudyQueue = useAppSelector((state) => state.studyQueue[category]);
  const intervalPreview = DUMMY_RATING_PREVIEW;

  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(initialStudyQueue || null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [currentCardDetail, setCurrentCardDetail] = useState<KoreanCardDetail | null>(null);
  const [error, setError] = useState<Error | null>(null);

  const locale = useLocale() as Locale;
  const repeat = async (rating: Rating) => {
    if (studyQueue === null || currentCard === null) return;
    const newStudyQueue = [
      ...studyQueue.filter((card) => card.userCardId !== currentCard.userCardId),
      {
        ...currentCard,
        studyInfo: {
          ...currentCard.studyInfo,
          state: rating === 'again' ? State.Learning : State.Review
        }
      }
    ];
    setStudyQueue(newStudyQueue);
    const nextCard = newStudyQueue.filter((card) => card.studyInfo.state !== State.Review)[0];
    setCurrentCard(nextCard ?? null);
  };

  useEffect(() => {
    const fetchCards = async () => {
      try {
        const response = await getLearningCards('new', category, accessToken ?? '');
        console.log('fetchCards', response);
        if (response && 'content' in response) {
          setStudyQueue(response.content);
          setCurrentCard(
            response.content.filter((card) => card.studyInfo.state !== State.Review)[0] ?? null
          );
        }
      } catch (error) {
        console.error('Error fetching cards:', error);
        setError(error as Error);
      }
    };
    if (studyQueue === null) {
      fetchCards();
    }
  }, [studyQueue, category, accessToken]);

  useEffect(() => {
    console.log('studyQueue:', studyQueue);
    // TODO: 서버, store 업데이트
  }, [studyQueue]);

  useEffect(() => {
    console.log('currentCard:', currentCard);

    const fetchCardDetail = async () => {
      if (currentCard) {
        const cardDetail = await getCardDetail(
          currentCard.koreanCard.cardId,
          locale,
          accessToken ?? ''
        );
        console.log('cardDetail', cardDetail);
        setCurrentCardDetail(cardDetail);
      }
    };

    fetchCardDetail();
  }, [currentCard, accessToken, locale]);

  return { currentCard, currentCardDetail, studyQueue, intervalPreview, repeat, error };
};
