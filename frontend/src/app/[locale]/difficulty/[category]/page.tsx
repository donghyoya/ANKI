'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import { useSelector } from 'react-redux';

import { getCardsFromDeck } from '@/api/decks';
import WordListPage from '@/components/common/WordListPage';

import { CardDetail, Paginated } from '@/types/schemes';
import { Locale } from '@/types/Locale';
import { Category } from '@/types/Category';
import { RootState } from '@/store';
import { refreshToken } from '@/api/utils';

export default function DifficultyWordsPage() {
  const { category, locale } = useParams() ?? {};
  const { accessToken } = useSelector((state: RootState) => state.auth);

  const [userCards, setUserCards] = useState<Paginated<CardDetail>>();

  // getCardsFromDeck는 accessToken이 optional이므로
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
      const cards = await getCardsFromDeck(
        locale as Locale,
        category as Category,
        accessToken ?? ''
      );
      if (cards) {
        setUserCards(cards);
      }
    };
    fetchUserCards();
  }, [category, accessToken, locale]);

  if (!userCards) {
    return <div>Loading...</div>;
  }

  return <WordListPage wordList={userCards.content} category={category as Category} />;
}
