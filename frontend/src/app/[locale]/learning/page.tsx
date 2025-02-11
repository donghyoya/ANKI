'use client';

import { useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';

import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';
import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';

import styles from './layout.module.scss';

import { DUMMY_CARD, DUMMY_PROGRESS, DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';

export default function LearningPage() {
  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: false,
    showConjugation: false,
    showExample: false
  });

  const cardStyle = useLearningCardLayout({
    contentHeight: document.querySelector(`.${styles['content-container']}`)?.scrollHeight ?? 0,
    cardWidth: document.querySelector(`.${styles['learning-card']}`)?.scrollWidth ?? 0
  });

  const handleReveal = () => {
    setCardState((prev) => ({ ...prev, isRevealed: true }));
  };

  const handleShowDetail = () => {
    setCardState((prev) => ({ ...prev, showDetail: true }));
  };

  const toggleConjugation = () => {
    setCardState((prev) => ({ ...prev, showConjugation: !prev.showConjugation }));
  };

  const toggleExample = () => {
    setCardState((prev) => ({ ...prev, showExample: !prev.showExample }));
  };

  return (
    <div className={styles['grid-container']}>
      <div className={styles['learning-container']}>
        <div className={styles['progress-container']}>
          <div className={styles['progress-title-container']}>
            <span className={styles['progress-title']}>Reviews</span>
          </div>
          <LearningProgressBar progress={DUMMY_PROGRESS} />
        </div>
        <LearningCard
          card={DUMMY_CARD}
          className={styles['learning-card']}
          cardState={cardState}
          handleReveal={handleReveal}
          handleShowDetail={handleShowDetail}
          toggleConjugation={toggleConjugation}
          toggleExample={toggleExample}
          style={cardStyle}
        />
        <RatingButtonContainer
          intervalPreview={DUMMY_RATING_PREVIEW}
          isRevealed={cardState.isRevealed}
        />
      </div>
    </div>
  );
}
