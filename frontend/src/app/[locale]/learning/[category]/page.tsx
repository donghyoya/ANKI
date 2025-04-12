'use client';

import { useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';

import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';
import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';

import styles from './layout.module.scss';

import { CardCategory } from '@/types/schemes';
import { useParams } from 'next/navigation';
import { useStudyQueue } from '@/hooks/useStudyQueue';
import { Rating } from '@/types/IntervalPreview';

import { MenuItem } from '@/types/Menu';
import { useTranslations } from 'next-intl';

export default function LearningPage() {
  const t = useTranslations();

  const { category } = useParams() ?? {};

  const [contentHeight, setContentHeight] = useState(0);

  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: false,
    showConjugation: false,
    showExample: false,
    isKoreanToForeign: true
  });

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
    return <div className={styles['page']}>Loading...</div>;
  }

  if (currentCard === null) {
    return <div className={styles['page']}>학습 끝</div>;
  }

  const handleOnRepeat = (rating: Rating) => {
    setCardState((prev) => ({ ...prev, isRevealed: false }));
    repeat(rating);
  };

  const toggleDetailedView = () => {
    setCardState((prev) => ({ ...prev, showDetail: !prev.showDetail }));
  };

  const toggleLangDirection = () => {
    setCardState((prev) => ({ ...prev, isKoreanToForeign: !prev.isKoreanToForeign }));
  };

  const handleRevertReveal = () => {
    setCardState((prev) => ({ ...prev, isRevealed: false }));
  };

  const menuItems: MenuItem[] = [
    {
      label: cardState.showDetail ? t('learning.hideDetails') : t('learning.showDetails'),
      onClick: toggleDetailedView
    },
    {
      label: cardState.isKoreanToForeign
        ? t('learning.foreignToKorean')
        : t('learning.koreanToForeign'),
      onClick: toggleLangDirection
    },
    ...(cardState.isRevealed
      ? [
          {
            label: t('learning.undoCheckAnswer'),
            onClick: handleRevertReveal
          }
        ]
      : [])
  ];

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
        menuItems={menuItems}
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
