'use client';

import { useEffect, useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';

import LearningCard from '@/components/LearningCard/LearningCard';

import styles from './layout.module.scss';

import { useParams } from 'next/navigation';

import { useQuery } from '@tanstack/react-query';
import { getKoreanCardDetail } from '@/api/cards';

export default function LearningPage() {
  const { cardId } = useParams() ?? {};
  const [contentHeight, setContentHeight] = useState(0);
  const [cardWidth, setCardWidth] = useState(0);

  const cardState = {
    isRevealed: true,
    showDetail: true,
    showConjugation: true,
    showExample: true,
    isKoreanToForeign: true
  };

  const cardStyle = useLearningCardLayout({
    contentHeight,
    cardWidth
  });

  const { data: cardDetail, isLoading } = useQuery({
    queryKey: ['card', cardId],
    queryFn: () => getKoreanCardDetail(+cardId)
  });

  useEffect(() => {
    setCardWidth(document.querySelector(`.${styles['learning-card']}`)?.scrollWidth ?? 0);
  }, []);

  if (isLoading || !cardDetail) return <div>Loading...</div>;

  return (
    <div className={styles['card-container']}>
      <LearningCard
        card={cardDetail}
        className={styles['learning-card']}
        cardState={cardState}
        handleReveal={() => {}}
        handleShowDetail={() => {}}
        toggleConjugation={() => {}}
        toggleExample={() => {}}
        style={cardStyle}
        menuItems={[]}
        setContentHeight={setContentHeight}
      />
    </div>
  );
}
