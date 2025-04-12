'use client';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';
import { useToken } from '@/hooks/useToken';
import { CardDetail, Paginated } from '@/types/schemes';
import { useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useState } from 'react';

export default function MeaningsWordsPage() {
  const { category } = useParams() ?? {};
  const { token } = useToken();

  const [userCards, setUserCards] = useState<Paginated<CardDetail>>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const cards = await getCardsFromDeck(category as string, token ?? '');
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [category, token]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={category as string} />;
}
