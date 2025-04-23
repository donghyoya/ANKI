'use client';

import React, { useEffect, useState } from 'react';

import { getDecks } from '@/api/decks';
import { useToken } from '@/hooks/useToken';
import { Deck } from '@/types/schemes';
import DeckListPage from '@/components/common/DeckListPage';

export default function DifficultyPage() {
  const { token } = useToken();

  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('difficulty', token ?? '');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, [token]);

  if (!decks) {
    return <div>Loading...</div>;
  }

  return <DeckListPage decks={decks} category="difficulty" />;
}
