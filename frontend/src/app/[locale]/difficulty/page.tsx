'use client';

import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';

import { Deck } from '@/types/schemes';
import { difficultiesInDisplayOrder } from '@/types/Category';
import { RootState } from '@/store';
import { refreshToken } from '@/api/utils';

export default function DifficultyPage() {
  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [decks, setDecks] = useState<Deck[]>();

  // getDecks는 accessToken이 optional이므로
  // 호출 전에 로그인 상태 확인(refreshToken 호출)
  // 토큰 갱신에 실패하더라도 오류 발생하지 않음
  useEffect(() => {
    const fetchUserCards = async () => {
      console.log('accessToken', accessToken);
      if (!accessToken) {
        try {
          await refreshToken();
        } catch (error) {
          console.error('토큰 갱신 실패', error);
        }
      }
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
