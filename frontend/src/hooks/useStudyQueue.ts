import { Category } from '@/types/Category';
import { useCardDetailCache } from './useCardDetailCache';
import { getLearningCards, postStudyInfo } from '@/api/study';
import { StudyService } from '@/services/StudyService';
import { Rating } from 'ts-fsrs';
import { useMutation, useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { UserCard } from '@/types/schemes';

export const useStudyQueue = (category: Category) => {
  const [queue, setQueue] = useState<UserCard[]>([]);
  const { currentCardDetail, isCardDetailLoading } = useCardDetailCache(queue);

  const { data: studyService } = useQuery({
    queryKey: ['studyService', category],
    queryFn: async () => {
      const newCards = await getLearningCards('new', category);
      const reviewCards = await getLearningCards('review', category);
      const service = new StudyService([...newCards.content, ...reviewCards.content]);
      setQueue([...service.queue]);
      return service;
    }
  });

  const ensureStudyService = () => {
    if (!studyService) {
      throw new Error('Study service not found');
    }
  };

  const repeatMutation = useMutation({
    mutationFn: async ({ rating }: { rating: Rating }) => {
      ensureStudyService();
      const newCard = studyService!.repeat(rating);
      setQueue([...studyService!.queue]);
      const response = await postStudyInfo(newCard.userCardId, newCard.studyInfo);
      return response;
    },
    onError: () => {
      ensureStudyService();
      studyService!.revert();
      setQueue([...studyService!.queue]);
    }
  });

  const repeat = (rating: Rating) => {
    repeatMutation.mutate({ rating });
  };

  const StateCounts = studyService?.StateCounts ?? {
    reviewCounts: 0,
    learningCounts: 0,
    overdueCounts: 0,
    newCounts: 0
  };
  const iPreview = studyService?.iPreview ?? null;
  const isCompleted = studyService?.isCompleted ?? false;
  const isLoading = !studyService || isCardDetailLoading;

  return {
    queue,
    iPreview,
    currentCardDetail,
    isLoading,
    isCompleted,
    StateCounts,
    repeat
  };
};
