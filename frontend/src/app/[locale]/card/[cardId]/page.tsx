'use client';

import LearningCard from '@/components/LearningCard/LearningCard';

import styles from './layout.module.scss';

import { useParams } from 'next/navigation';

import { useQuery } from '@tanstack/react-query';
import { getKoreanCardDetail } from '@/api/cards';

export default function LearningPage() {
  const { cardId } = useParams() ?? {};

  const cardState = {
    isRevealed: true,
    showDetail: true,
    showConjugation: true,
    showExample: true,
    isKoreanToForeign: true
  };

  const { data: cardDetail, isLoading } = useQuery({
    queryKey: ['card', cardId],
    queryFn: () => getKoreanCardDetail(+cardId)
  });

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
        menuItems={[]}
      />
    </div>
  );
}
