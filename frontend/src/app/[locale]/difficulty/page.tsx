'use client';

import React, { useEffect, useState } from 'react';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';
import { useValidatedToken } from '@/hooks/useValidatedToken';

import { Deck } from '@/types/schemes';
import { difficultiesInDisplayOrder } from '@/types/Category';

export default function DifficultyPage() {
  const { isRefreshing, accessToken } = useValidatedToken();
  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      if (isRefreshing) return;

      const fetchedDecks = await getDecks('difficulty', accessToken ?? '');
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
    <DeckListPage
      decks={decks}
      categoryType="difficulty"
      displayOrder={difficultiesInDisplayOrder}
    />
  );
}
