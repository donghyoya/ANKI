import { useAppSelector } from '@/store/hooks';
import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import { KoreanCardDetail, UserCard } from '@/types/schemes';
import { Rating } from '@/types/IntervalPreview';
import { Category } from '@/types/Category';

import { getUserCards } from '@/api/study';
import { RootState } from '@/store';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import { State } from 'ts-fsrs';

export const useStudyQueue = (category: Category) => {
  const initialStudyQueue = useAppSelector((state) => state.studyQueue[category]);
  const intervalPreview = DUMMY_RATING_PREVIEW;

  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(initialStudyQueue || null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [currentCardDetail, setCurrentCardDetail] = useState<KoreanCardDetail | null>(null);
  const [error, setError] = useState<Error | null>(null);

  const repeat = (rating: Rating) => {
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
    setCurrentCard(
      newStudyQueue.filter((card) => card.studyInfo.state !== State.Review)[0] ?? null
    );
  };

  useEffect(() => {
    const fetchCards = async () => {
      try {
        const response = await getUserCards('new', category, accessToken ?? '');
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
  }, [currentCard]);

  return { currentCard, currentCardDetail, studyQueue, intervalPreview, repeat, error };
};
