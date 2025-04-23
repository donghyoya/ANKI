'use client';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';
import { useToken } from '@/hooks/useToken';
import { CardDetail, Paginated } from '@/types/schemes';
import { useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useState } from 'react';
import { Locale } from '@/types/Locale';
import { Category } from '@/types/Category';

export default function DifficultyWordsPage() {
  const { category } = useParams() ?? {};
  const { token } = useToken();
  const { locale } = useParams() ?? {};

  const [userCards, setUserCards] = useState<Paginated<CardDetail>>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const cards = await getCardsFromDeck(locale as Locale, category as Category, token ?? '');
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [category, token, locale]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={category as Category} />;
}
