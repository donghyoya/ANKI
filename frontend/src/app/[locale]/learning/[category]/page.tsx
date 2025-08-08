'use client';

import { useEffect, useState } from 'react';
import { redirect, useParams } from 'next/navigation';
import { useStudyQueue } from '@/hooks/useStudyQueue';
import { useTranslations } from 'next-intl';

import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';
import RatingButtonContainer from '@/components/RatingButton/RatingButtonContainer';
import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';
import CustomDialog from '@/components/Dialogs/CustomDialog';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';

import { Category, getCategoryType } from '@/types/Category';
import { MenuItem } from '@/types/Menu';
import { Rating } from 'ts-fsrs';

import styles from './layout.module.scss';
import LearningCardSlider from '@/components/LearningCardSlider/LearningCardSlider';
import { motion } from 'motion/react';

export default function LearningPage() {
  const t = useTranslations();
  const { category } = useParams() ?? {};

  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: false,
    showDetail: false,
    showConjugation: false,
    showExample: false,
    isKoreanToForeign: true
  });

  const {
    queue,
    currentCardDetail,
    isLoading,
    repeat,
    StateCounts,
    iPreview,
    isCompleted,
    error,
    clearError
  } = useStudyQueue(category as Category);

  const [index, setIndex] = useState(0);
  const [direction, setDirection] = useState<1 | -1>(1);

  // const handlePrev = () => {
  //   if (index === 0) return;
  //   setDirection(-1);
  //   setIndex((prev) => prev - 1);
  // };

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

  const handleOnRepeat = async (rating: Rating) => {
    if (index === queue.length - 1) return;
    console.log('onRepeat');
    setDirection(1);
    setIndex((prev) => prev + 1);
    repeat(rating);
    setCardState((prev) => ({ ...prev, isRevealed: false }));
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
    if (error) {
      alert(error.message);
      clearError();
    }
  }, [error, clearError]);

  if (isLoading) {
    return (
      <div className={styles['page']}>
        <LoadingSpinner />
      </div>
    );
  }

  if (!currentCardDetail) {
    return <div className={styles['page']}>CardDetail Loading...</div>;
  }

  if (!queue) {
    return <div className={styles['page']}>Study queue not found</div>;
  }

  return (
    <motion.div className={styles['learning-container']} layout>
      <div className={styles['progress-container-wrapper']}>
        <div className={styles['progress-container']}>
          <LearningProgressBar className={styles['progress-bar']} StateCounts={StateCounts} />
        </div>
      </div>
      <LearningCardSlider direction={direction} index={index}>
        <LearningCard
          card={currentCardDetail}
          className={styles['learning-card']}
          cardState={cardState}
          handleReveal={handleReveal}
          handleShowDetail={handleShowDetail}
          toggleConjugation={toggleConjugation}
          toggleExample={toggleExample}
          menuItems={menuItems}
        />
      </LearningCardSlider>
      <RatingButtonContainer
        iPreview={iPreview}
        isRevealed={cardState.isRevealed}
        onRepeat={handleOnRepeat}
      />
      {isCompleted && (
        <CustomDialog
          open={isCompleted}
          headline="Daily goal completed!"
          prompt={
            <div>
              Want to keep going?
              <br />
              Choose an option below:
            </div>
          }
          firstButtonString="Learn More"
          secondButtonString="Finish"
          firstButtonOnclick={() => {
            alert('Not implemented');
          }}
          secondButtonOnclick={() => {
            redirect(`/${getCategoryType(category as Category)}`);
          }}
        />
      )}
    </motion.div>
  );
}
