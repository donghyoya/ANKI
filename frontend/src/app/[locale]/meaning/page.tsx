'use client';

import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

import DeckListPage from '@/components/common/DeckListPage';
import { meaningInDisplayOrder } from '@/types/Category';
import { useToken } from '@/hooks/useToken';
import { getDecks } from '@/api/decks';
import { Deck } from '@/types/schemes';
import { RootState } from '@/store';

export default function MeaningsPage() {
  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [decks, setDecks] = useState<Deck[]>();

  useEffect(() => {
    if (!accessToken) return;
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('meaning', accessToken);
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();
  }, [accessToken]);

  if (!decks) {
    return <div>Loading...</div>;
  }
  return <DeckListPage decks={decks} categoryType="meaning" displayOrder={meaningInDisplayOrder} />;
}
