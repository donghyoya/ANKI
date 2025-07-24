import { getKoreanCardDetail } from '@/api/cards';
import { UserCard } from '@/types/schemes';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import { useEffect } from 'react';

// currentCardDetail 쿼리
// prefetch 로직

const CACHE_SIZE = 5;

export const useCardDetailCache = (queue: readonly UserCard[]) => {
  const currentCard = queue[0];

  const {
    data: currentCardDetail,
    isPending: isCardDetailLoading,
    error: cardDetailError
  } = useQuery({
    queryKey: ['cardDetail', currentCard?.koreanCard?.cardId],
    queryFn: async () => {
      if (!currentCard) return null;
      console.log('fetching cardDetail', currentCard.koreanCard.koreanWord);
      return await getKoreanCardDetail(currentCard!.koreanCard.cardId);
    },
    enabled: !!currentCard?.koreanCard?.cardId,
    staleTime: Infinity
  });

  const queryClient = useQueryClient();

  useEffect(() => {
    // 캐시 사이즈만큼 프리패치
    queue.slice(0, CACHE_SIZE).forEach((card) => {
      queryClient.prefetchQuery({
        queryKey: ['cardDetail', card.koreanCard.cardId],
        queryFn: () => {
          console.log('prefetching cardDetail', card.koreanCard.koreanWord);
          try {
            return getKoreanCardDetail(card.koreanCard.cardId);
          } catch (error) {
            // 에러 발생시 쿼리 자신을 캐시에서 제거
            // prefetch에서 발생한 에러이므로 에러를 throw하지 않음
            console.error('Prefetch failed for card:', card.koreanCard.koreanWord, error);
            queryClient.removeQueries({
              queryKey: ['cardDetail', card.koreanCard.cardId]
            });
          }
        },
        staleTime: Infinity
      });
    });
  }, [queue, queryClient]);

  return { currentCardDetail, isCardDetailLoading, cardDetailError };
};
