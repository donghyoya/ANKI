'use client';

import DeckListPage from '@/components/common/DeckListPage';
import { meaningInDisplayOrder } from '@/types/Category';
import { useState } from 'react';
import { useEffect } from 'react';
import { useToken } from '@/hooks/useToken';
import { getDecks } from '@/api/decks';
import { Deck } from '@/types/schemes';

export default function MeaningPage() {
  const { token } = useToken();

  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('meaning', token ?? '');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, [token]);

  if (!decks) {
    return <div>Loading...</div>;
  }
  return (
    <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningInDisplayOrder} />
  );
}
