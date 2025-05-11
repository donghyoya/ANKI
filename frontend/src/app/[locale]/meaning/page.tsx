'use client';

import { useState, useEffect } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import { meaningInDisplayOrder } from '@/types/Category';
import { useValidatedToken } from '@/hooks/useValidatedToken';
import { Deck } from '@/types/schemes';
import { getDecks } from '@/api/decks';

export default function MeaningsPage() {
  const { isRefreshing, accessToken } = useValidatedToken();
  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      if (isRefreshing) return;

      const fetchedDecks = await getDecks('meaning', accessToken ?? '');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, [accessToken, isRefreshing]);

  if (!decks) {
    return <div>Loading...</div>;
  }
  return <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningInDisplayOrder} />;
}
