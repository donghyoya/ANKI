import { getKoreanCardDetail } from '@/api/cards';
import { UserCard } from '@/types/schemes';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import { useEffect } from 'react';

// currentCardDetail 쿼리
// prefetch 로직

export const useCardDetailCache = (queue: readonly UserCard[]) => {
  const currentCard = queue[0];

  const { data: currentCardDetail, isPending: isCardDetailLoading } = useQuery({
    queryKey: ['cardDetail', currentCard?.koreanCard?.cardId],
    queryFn: () => {
      if (!currentCard) {
        return null;
      }
      return getKoreanCardDetail(currentCard!.koreanCard.cardId);
    },
    enabled: !!currentCard?.koreanCard?.cardId,
    staleTime: Infinity
  });

  const queryClient = useQueryClient();

  useEffect(() => {
    queue.forEach((card) => {
      queryClient.prefetchQuery({
        queryKey: ['cardDetail', card.koreanCard.cardId],
        queryFn: () => {
          console.log('prefetching cardDetail', card.koreanCard.koreanWord);
          return getKoreanCardDetail(card.koreanCard.cardId);
        },
        staleTime: Infinity
      });
    });
  }, [queue, queryClient]);

  return { currentCardDetail, isCardDetailLoading };
};
