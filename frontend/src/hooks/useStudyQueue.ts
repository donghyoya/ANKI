import { Category } from '@/types/Category';
import { useCardDetailCache } from './useCardDetailCache';
import { getLearningCards, postStudyInfo } from '@/api/study';
import { StudyService } from '@/services/StudyService';
import { Rating } from 'ts-fsrs';
import { useMutation, useQuery } from '@tanstack/react-query';
import { useEffect, useState } from 'react';
import { UserCard } from '@/types/schemes';

export const useStudyQueue = (category: Category) => {
  const [queue, setQueue] = useState<UserCard[]>([]);
  const [error, setError] = useState<Error | null>(null);
  const { currentCardDetail, isCardDetailLoading, cardDetailError } = useCardDetailCache(queue);

  const { data: studyService } = useQuery({
    queryKey: ['studyService', category],
    queryFn: async () => {
      try {
        const newCards = await getLearningCards('new', category);
        const reviewCards = await getLearningCards('review', category);
        const service = new StudyService([...newCards.content, ...reviewCards.content]);
        setQueue([...service.queue]);
        return service;
      } catch (error) {
        setError(error as Error);
      }
    }
  });

  const withStudyService = <T>(
    studyService: StudyService | undefined,
    callback: (service: StudyService) => T
  ): T => {
    if (!studyService) {
      throw new Error('Study service not found');
    }
    return callback(studyService);
  };

  const repeatMutation = useMutation({
    mutationFn: async ({ rating }: { rating: Rating }) => {
      return withStudyService(studyService, (service) => {
        const newCard = service.repeat(rating);
        setQueue([...service.queue]);
        const response = postStudyInfo(newCard.userCardId, newCard.studyInfo);
        return response;
      });
    },
    onError: (error) => {
      withStudyService(studyService, (service) => {
        service.revert();
        setQueue([...service.queue]);
        setError(error);
      });
    }
  });

  const repeat = async (rating: Rating) => {
    await repeatMutation.mutateAsync({ rating });
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

  useEffect(() => {
    if (cardDetailError) {
      setError(cardDetailError);
    }
  }, [cardDetailError]);

  const clearError = () => {
    setError(null);
  };

  return {
    queue,
    iPreview,
    currentCardDetail,
    isLoading,
    isCompleted,
    StateCounts,
    repeat,
    error,
    clearError
  };
};
