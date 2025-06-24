import { Category } from '@/types/Category';
import { useCardDetailCache } from './useCardDetailCache';
import { getLearningCards } from '@/api/study';
import { useEffect, useState } from 'react';
import { StudyService } from '@/services/StudyService';

export const useStudyQueue = (category: Category) => {
  const [studyService, setStudyService] = useState<StudyService | null>(null);
  const { currentCardDetail, isCardDetailLoading } = useCardDetailCache(studyService?.queue ?? []);

  useEffect(() => {
    const initializeService = async () => {
      const newCards = await getLearningCards('new', category);
      const reviewCards = await getLearningCards('review', category);
      setStudyService(new StudyService([...newCards.content, ...reviewCards.content]));
    };

    initializeService();
  }, [category]);

  const isLoading = !studyService || isCardDetailLoading;

  return {
    studyService,
    currentCardDetail,
    isLoading
  };
};
