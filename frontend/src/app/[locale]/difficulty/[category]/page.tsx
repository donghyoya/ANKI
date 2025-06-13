'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { KoreanCardWithForeignWords, Paginated } from '@/types/schemes';
import { Category } from '@/types/Category';
import { Locale } from '@/types/Locale';

export default function DifficultyWordsPage() {
  const { category, locale } = useParams() ?? {};

  const [userCards, setUserCards] = useState<Paginated<KoreanCardWithForeignWords>>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const cards = await getCardsFromDeck(locale as Locale, category as Category);
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [category, locale]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={category as Category} />;
}
