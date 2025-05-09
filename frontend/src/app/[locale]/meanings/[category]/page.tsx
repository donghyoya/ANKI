'use client';

import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useSelector } from 'react-redux';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { Locale } from '@/types/Locale';
import { Category } from '@/types/Category';
import { CardDetail, Paginated } from '@/types/schemes';
import { RootState } from '@/store';

export default function MeaningsWordsPage() {
  const { category, locale } = useParams() ?? {};
  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [userCards, setUserCards] = useState<Paginated<CardDetail>>();

  useEffect(() => {
    if (!accessToken) return;
    const fetchUserCards = async () => {
      const cards = await getCardsFromDeck(locale as Locale, category as Category, accessToken);
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [category, accessToken, locale]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={category as Category} />;
}
