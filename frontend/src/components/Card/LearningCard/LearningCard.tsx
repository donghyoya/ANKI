'use client';

import { useState } from 'react';
import { FilledCard } from '../Card';

import { Card } from '@/types/Card';

import styles from './LearningCard.module.scss';
import ConjugationSection from './ConjugationSection';
import ExampleSection from './ExampleSection';
import WordSection from './WordSection';

interface LearningCardProps {
  card: Card;
}

const LearningCard = ({ card }: LearningCardProps) => {
  const [isRevealed, setIsRevealed] = useState(false);
  const [showDetail, setShowDetail] = useState(true);

  const handleReveal = () => {
    setIsRevealed(true);
  };

  return (
    <FilledCard className={styles['card']} ripple={false} onClick={handleReveal}>
      {!isRevealed && (
        <div className={styles['simple-content-container']}>
          <span
            className={`${styles['simple-content-container-header-korean']} md-typescale-headline-large`}
          >
            {card.wordInfo.koreanWord}
          </span>
          <span
            className={`${styles['simple-content-container-header-foreign']} md-typescale-headline-small ${styles['revealed']}`}
          >
            Check Answer
          </span>
        </div>
      )}

      {isRevealed && !showDetail && (
        <div className={styles['simple-content-container']}>
          <span
            className={`${styles['simple-content-container-header-korean']} md-typescale-headline-large`}
          >
            {card.wordInfo.koreanWord}
          </span>
          <span
            className={`${styles['simple-content-container-header-foreign']} md-typescale-headline-small`}
          >
            {card.wordInfo.foreignWord}
          </span>
        </div>
      )}

      {isRevealed && showDetail && (
        <div className={styles['detail-content-container']}>
          <WordSection wordInfo={card.wordInfo} />
          <ConjugationSection conjugations={card.wordInfo.inflection} />
          <ExampleSection examples={card.example} />
        </div>
      )}
    </FilledCard>
  );
};

export default LearningCard;
