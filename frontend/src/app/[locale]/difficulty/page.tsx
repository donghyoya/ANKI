'use client';

import React, { useEffect, useState } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';

import { Deck } from '@/types/schemes';
import { difficultiesInDisplayOrder } from '@/types/Category';

export default function DifficultyPage() {
  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('difficulty');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, []);

  if (!decks) {
    return <div>Loading...</div>;
  }

  return (
    <DeckListPage
      decks={decks}
      categoryType="difficulty"
      displayOrder={difficultiesInDisplayOrder}
    />
  );
}
