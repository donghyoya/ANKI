'use client';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';
import { useToken } from '@/hooks/useToken';
import { CardDetail, Paginated } from '@/types/schemes';
import { useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useState } from 'react';

export default function DifficultyWordsPage() {
  const { level } = useParams() ?? {};
  const { token } = useToken();

  const [userCards, setUserCards] = useState<Paginated<CardDetail>>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const cards = await getCardsFromDeck(level as string, token ?? '');
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [level, token]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={level as string} />;
}
