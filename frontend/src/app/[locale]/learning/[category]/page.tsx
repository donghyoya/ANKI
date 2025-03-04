'use client';

import { useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';

import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';
import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';

import styles from './layout.module.scss';

import { DUMMY_MENU_ITEMS } from '@/utils/dummyData';
import { CardCategory } from '@/types/schemes';
import { useParams } from 'next/navigation';
import { useStudyQueue } from '@/hooks/useStudyQueue';
import { Rating } from '@/types/IntervalPreview';

export default function LearningPage() {
  const { category } = useParams() ?? {};

  const [contentHeight, setContentHeight] = useState(0);

  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: false,
    showConjugation: false,
    showExample: false
  });

  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [learningType, setLearningType] = useState<'reviews' | 'news'>('reviews');

  const cardStyle = useLearningCardLayout({
    contentHeight,
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

  const { currentCard, studyQueue, intervalPreview, repeat } = useStudyQueue(
    category as CardCategory
  );

  if (studyQueue === null) {
    return <div>Loading...</div>;
  }

  if (currentCard === null) {
    return <div>학습 끝</div>;
  }

  const handleOnRepeat = (rating: Rating) => {
    setCardState((prev) => ({ ...prev, isRevealed: false }));
    repeat(rating);
  };

  return (
    <div className={styles['learning-container']}>
      <div className={styles['progress-container-wrapper']}>
        <div className={styles['progress-container']}>
          <LearningProgressBar className={styles['progress-bar']} userCards={studyQueue} />
        </div>
      </div>
      <LearningCard
        card={currentCard}
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
        intervalPreview={intervalPreview}
        isRevealed={cardState.isRevealed}
        onRepeat={handleOnRepeat}
      />
    </div>
  );
}
