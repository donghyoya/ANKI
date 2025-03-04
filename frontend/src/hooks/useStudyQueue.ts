import { useAppSelector } from '@/store/hooks';
import { useEffect, useState } from 'react';
import { CardCategory, UserCard } from '@/types/schemes';
import { mockGetUserCards } from '@/api/mock';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import { Rating } from '@/types/IntervalPreview';

export const useStudyQueue = (category: CardCategory) => {
  const initialStudyQueue = useAppSelector((state) => state.studyQueue[category]);
  const intervalPreview = DUMMY_RATING_PREVIEW;

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(initialStudyQueue || null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);

  const fetchCards = async () => {
    const response = await mockGetUserCards();
    if (response && 'content' in response) {
      setStudyQueue(response.content);
      setCurrentCard(response.content.filter((card) => card.state !== 'matured')[0] ?? null);
    }
  };

  const repeat = (rating: Rating) => {
    if (studyQueue === null || currentCard === null) return;
    const newStudyQueue = [
      ...studyQueue.filter((card) => card.cardId !== currentCard.cardId),
      { ...currentCard, state: rating === ('again' as Rating) ? 'learning' : 'matured' }
    ];
    setStudyQueue(newStudyQueue);
    setCurrentCard(newStudyQueue.filter((card) => card.state !== 'matured')[0] ?? null);
  };

  useEffect(() => {
    if (studyQueue === null) {
      fetchCards();
    }
  }, [studyQueue]);

  useEffect(() => {
    console.log('studyQueue:', studyQueue);
    // TODO: 서버, store 업데이트
  }, [studyQueue]);

  useEffect(() => {
    console.log('currentCard:', currentCard);
  }, [currentCard]);

  return { currentCard, studyQueue, intervalPreview, repeat };
};
