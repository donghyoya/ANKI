'use client';

import { useParams } from 'next/navigation';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';

import { Category } from '@/types/Category';
import { Locale } from '@/types/Locale';
import { useInfiniteQuery } from '@tanstack/react-query';

export default function DifficultyWordsPage() {
  const { category, locale } = useParams() ?? {};

  const { data: cards } = useInfiniteQuery({
    queryKey: ['cards', category, locale],
    queryFn: async ({ pageParam = 0 }) => {
      const cards = await getCardsFromDeck(locale as Locale, category as Category, pageParam + 1);
      console.log(cards.content.map((c) => c.koreanWord));
      return cards;
    },
    getNextPageParam: (lastPage) => lastPage.page + 1,
    initialPageParam: 0
  });

  const content = cards?.pages.map((page) => page.content).flat();

  if (!content) {
    return <LoadingSpinner />;
  }

  return <WordListPage wordList={content} category={category as Category} />;
}
