'use client';

import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';

import { getKoreanCardDetailsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { Category } from '@/types/Category';
import { KoreanCardDetail, Paginated } from '@/types/schemes';

export default function MeaningWordsPage() {
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
