'use client';

import { useEffect, useState } from 'react';
import useLearningCardLayout from '@/hooks/useLearningCardLayout';
import { useParams } from 'next/navigation';
import { useStudyQueue } from '@/hooks/useStudyQueue';
import { useTranslations } from 'next-intl';

import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';
import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';

import { Category } from '@/types/Category';
import { MenuItem } from '@/types/Menu';
import { Rating } from 'ts-fsrs';

import styles from './layout.module.scss';

export default function LearningPage() {
  const t = useTranslations();
  const { category } = useParams() ?? {};
  const [contentHeight, setContentHeight] = useState(0);
  const [cardWidth, setCardWidth] = useState(0);

  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: false,
    showConjugation: false,
    showExample: false,
    isKoreanToForeign: true
  });

  const cardStyle = useLearningCardLayout({
    contentHeight,
    cardWidth
  });

  const { studyService, currentCardDetail, isLoading } = useStudyQueue(category as Category);

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

  const handleOnRepeat = (rating: Rating) => {
    if (!studyService) {
      throw new Error('Study service not found');
    }
    setCardState((prev) => ({ ...prev, isRevealed: false }));
    studyService.repeat(rating);
    studyService.updateQueue(studyService.currentCard);
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

  useEffect(() => {
    setCardWidth(document.querySelector(`.${styles['learning-card']}`)?.scrollWidth ?? 0);
  }, []);

  if (isLoading && !studyService) {
    return <div className={styles['page']}>Loading...</div>;
  }

  if (!currentCardDetail) {
    return <div className={styles['page']}>Card not found</div>;
  }

  if (!studyService?.queue) {
    return <div className={styles['page']}>Study queue not found</div>;
  }

  if (studyService.isCompleted) {
    return <div className={styles['page']}>학습 끝</div>;
  }

  return (
    <div className={styles['learning-container']}>
      <div className={styles['progress-container-wrapper']}>
        <div className={styles['progress-container']}>
          <LearningProgressBar
            className={styles['progress-bar']}
            studyCounts={studyService.studyCounts}
          />
        </div>
      </div>
      <LearningCard
        card={currentCardDetail}
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
        iPreview={studyService.iPreview}
        isRevealed={cardState.isRevealed}
        onRepeat={handleOnRepeat}
      />
    </div>
  );
}
