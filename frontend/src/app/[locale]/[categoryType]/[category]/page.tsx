'use client';

import { useParams } from 'next/navigation';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';

import { Category } from '@/types/Category';
import { Locale } from '@/types/Locale';
import { useInfiniteQuery } from '@tanstack/react-query';

export default function CategoryPage() {
  const { category, locale } = useParams() ?? {};

  const {
    data: cards,
    fetchNextPage,
    isFetchingNextPage,
    hasNextPage
  } = useInfiniteQuery({
    queryKey: ['cards', category, locale],
    queryFn: async ({ pageParam = 1 }) => {
      const cards = await getCardsFromDeck(locale as Locale, category as Category, pageParam);
      console.log(cards.content.map((c) => c.koreanWord));
      return cards;
    },
    getNextPageParam: (lastPage) => {
      return lastPage.content.length > 0 ? lastPage.page + 1 : undefined;
    },
    initialPageParam: 1
  });

  const content = cards?.pages.map((page) => page.content).flat();

  if (!content) {
    return <LoadingSpinner />;
  }

  return (
    <WordListPage
      wordList={content}
      category={category as Category}
      onLoadMore={fetchNextPage}
      isLoading={isFetchingNextPage}
      hasMore={hasNextPage}
    />
  );
}
