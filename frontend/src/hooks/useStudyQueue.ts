import { useAppSelector } from '@/store/hooks';
import { useEffect, useState } from 'react';
import { useToken } from '@/hooks/useToken';

import { UserCard } from '@/types/schemes';
import { Rating } from '@/types/IntervalPreview';
import { Category } from '@/types/Category';

import { getUserCards } from '@/api/study';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';

export const useStudyQueue = (category: Category) => {
  const initialStudyQueue = useAppSelector((state) => state.studyQueue[category]);
  const intervalPreview = DUMMY_RATING_PREVIEW;
  const { token } = useToken();

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(initialStudyQueue || null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [error, setError] = useState<Error | null>(null);

  const repeat = (rating: Rating) => {
    if (studyQueue === null || currentCard === null) return;
    const newStudyQueue = [
      ...studyQueue.filter((card) => card.cardId !== currentCard.cardId),
      { ...currentCard, state: rating === ('again' as Rating) ? 'Learning' : 'Matured' }
    ];
    setStudyQueue(newStudyQueue);
    setCurrentCard(
      newStudyQueue.filter((card) => card.fsrsParameters.state !== 'Matured')[0] ?? null
    );
  };

  useEffect(() => {
    const fetchCards = async () => {
      if (!token) return;
      try {
        const response = await getUserCards('new', category, token);
        console.log('fetchCards', response);
        if (response && 'content' in response) {
          setStudyQueue(response.content);
          setCurrentCard(
            response.content.filter((card) => card.fsrsParameters.state !== 'Matured')[0] ?? null
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
  }, [studyQueue, category, token]);

  useEffect(() => {
    console.log('studyQueue:', studyQueue);
    // TODO: 서버, store 업데이트
  }, [studyQueue]);

  useEffect(() => {
    console.log('currentCard:', currentCard);
  }, [currentCard]);

  return { currentCard, studyQueue, intervalPreview, repeat, error };
};
