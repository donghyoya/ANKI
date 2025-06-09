'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

import { getKoreanCardDetailsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { KoreanCardDetail, Paginated } from '@/types/schemes';
import { Category } from '@/types/Category';

export default function DifficultyWordsPage() {
  const { category, locale } = useParams() ?? {};

  const [userCards, setUserCards] = useState<Paginated<KoreanCardDetail>>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const cards = await getKoreanCardDetailsFromDeck();
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
