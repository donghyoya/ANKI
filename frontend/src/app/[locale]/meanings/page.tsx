'use client';

import { useState, useEffect } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';
import { useValidatedToken } from '@/hooks/useValidatedToken';

import { meaningsInDisplayOrder } from '@/types/Category';
import { Deck } from '@/types/schemes';

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

  return (
    <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningsInDisplayOrder} />
  );
}
