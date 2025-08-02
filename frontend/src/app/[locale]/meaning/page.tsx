'use client';

import { useState, useEffect } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';
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
    return <LoadingSpinner />;
  }
  return <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningInDisplayOrder} />;
}
