'use client';

import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';

import { Deck } from '@/types/schemes';
import { difficultiesInDisplayOrder } from '@/types/Category';
import { RootState } from '@/store';

export default function DifficultyPage() {
  const { accessToken } = useSelector((state: RootState) => state.auth);
  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('difficulty', accessToken ?? '');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, [accessToken]);

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
