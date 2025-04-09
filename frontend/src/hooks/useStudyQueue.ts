import { useAppSelector } from '@/store/hooks';
import { useEffect, useState } from 'react';
import { CardCategory, UserCard } from '@/types/schemes';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import { Rating } from '@/types/IntervalPreview';
import { getUserCards } from '@/api/study';
import { useToken } from '@/hooks/useToken';

export const useStudyQueue = (category: CardCategory) => {
  const initialStudyQueue = useAppSelector((state) => state.studyQueue[category]);
  const intervalPreview = DUMMY_RATING_PREVIEW;
  const { token } = useToken();

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(initialStudyQueue || null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);

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
    const fetchCards = async () => {
      if (!token) return;
      const response = await getUserCards('new', category, token);
      console.log('fetchCards', response);
      if (response && 'content' in response) {
        setStudyQueue(response.content);
        setCurrentCard(response.content.filter((card) => card.state !== 'matured')[0] ?? null);
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

  return { currentCard, studyQueue, intervalPreview, repeat };
};
