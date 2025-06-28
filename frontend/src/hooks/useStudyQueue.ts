import { Category } from '@/types/Category';
import { useCardDetailCache } from './useCardDetailCache';
import { getLearningCards, postStudyInfo } from '@/api/study';
import { useEffect, useState } from 'react';
import { StudyService } from '@/services/StudyService';
import { Rating } from 'ts-fsrs';

export const useStudyQueue = (category: Category) => {
  const [studyService, setStudyService] = useState<StudyService | null>(null);
  const { currentCardDetail, isCardDetailLoading } = useCardDetailCache(studyService?.queue ?? []);

  const repeat = async (rating: Rating) => {
    if (!studyService) {
      throw new Error('Study service not found');
    }

    // 백업을 미리 만들고 시작
    const backupQueue = [...studyService.queue];
    const newCard = studyService.repeat(rating);

    try {
      const response = await postStudyInfo(newCard.userCardId, newCard.studyInfo);
      return response;
    } catch {
      // 새로운 StudyService 인스턴스로 교체
      const revertedService = new StudyService(backupQueue);
      setStudyService(revertedService);
      alert('repeat failed');
    }
  };

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
    isLoading,
    repeat
  };
};
