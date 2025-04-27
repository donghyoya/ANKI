'use client';

import { useToken } from '@/hooks/useToken';
import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { Locale } from '@/types/Locale';
import { Category } from '@/types/Category';
import { CardDetail, Paginated } from '@/types/schemes';

export default function MeaningsWordsPage() {
  const { category, locale } = useParams() ?? {};
  const { token } = useToken();

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
