'use client';

import { useState, useEffect } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';

import { meaningInDisplayOrder } from '@/types/Category';
import { Deck } from '@/types/schemes';

export default function MeaningsPage() {
  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('meaning');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, []);

  if (!decks) {
    return <div>Loading...</div>;
  }
  return <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningInDisplayOrder} />;
}
