'use client';

import { useEffect, useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';

import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';
import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';

import styles from './layout.module.scss';

import {
  DUMMY_CARD,
  DUMMY_PROGRESS,
  DUMMY_RATING_PREVIEW,
  DUMMY_MENU_ITEMS
} from '@/utils/dummyData';

export default function LearningPage() {
  const [contentHeight, setContentHeight] = useState(0);

  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: true,
    showConjugation: false,
    showExample: false
  });

  const [learningType, setLearningType] = useState<'reviews' | 'news'>('reviews');

  const cardStyle = useLearningCardLayout({
    contentHeight,
    cardWidth: document.querySelector(`.${styles['learning-card']}`)?.scrollWidth ?? 0
  });

  useEffect(() => {
    console.log('cardStyle:', cardStyle);
  }, [cardStyle]);

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
    <div className={styles['learning-container']}>
      <div className={styles['progress-container-wrapper']}>
        <div className={styles['progress-container']}>
          <div className={styles['progress-label-container']}>
            <span
              className={`${styles['progress-label']} ${learningType === 'reviews' ? styles['active'] : ''}`}
            >
              Reviews
            </span>
          </div>
          <LearningProgressBar className={styles['progress-bar']} progress={DUMMY_PROGRESS} />
        </div>
        <div className={styles['progress-container']}>
          <div className={styles['progress-label-container']}>
            <span
              className={`${styles['progress-label']} ${learningType === 'news' ? styles['active'] : ''}`}
            >
              News
            </span>
          </div>
          <LearningProgressBar className={styles['progress-bar']} progress={DUMMY_PROGRESS} />
        </div>
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
        menuItems={DUMMY_MENU_ITEMS}
        setContentHeight={setContentHeight}
      />
      <RatingButtonContainer
        intervalPreview={DUMMY_RATING_PREVIEW}
        isRevealed={cardState.isRevealed}
      />
    </div>
  );
}
