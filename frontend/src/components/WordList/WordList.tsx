'use client';

import React, { useState, useMemo } from 'react';

import { OutlinedCard } from '../Card/Card';
import LearningCard, { LearningCardState } from '@/components/LearningCard/LearningCard';

import { MenuItem } from '@/types/Menu';
import { DUMMY_KOR_CARD_DETAIL } from '@/utils/dummyData';

import styles from './WordList.module.scss';
import { WordListProps } from './types';

const WordList = ({
  KoreanWord,
  ForeignWord,
  isHideKorean,
  isHideForeign,
  homographNumber
}: WordListProps) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [contentHeight, setContentHeight] = useState(0);
  const [cardState, setCardState] = useState<LearningCardState>({
    isRevealed: true,
    showDetail: true,
    showConjugation: false,
    showExample: false
  });

  const handleClick = () => {
    setIsExpanded((prev) => !prev);
  };

  const toggleConjugation = () => {
    setCardState((prev) => ({ ...prev, showConjugation: !prev.showConjugation }));
  };

  const toggleExample = () => {
    setCardState((prev) => ({ ...prev, showExample: !prev.showExample }));
  };

  const menu_items: MenuItem[] = useMemo(
    () => [
      { label: '사과', onClick: () => {} },
      { label: '바나나', onClick: () => {} },
      { label: 'cancel', onClick: handleClick }
    ],
    []
  );

  return (
    <div>
      {!isExpanded && (
        <OutlinedCard className={styles.card} onClick={handleClick}>
          <div className={styles.content}>
            <h3 className={styles['korean-word']}>
              {isHideKorean ? '' : KoreanWord}
              <span className={styles['homograph-number']}>{homographNumber}</span>
            </h3>
            <div className={styles['right-container']}>
              <div className={styles.line}></div>
              <h3 className={styles['foreign-word']}>{isHideForeign ? '' : ForeignWord}</h3>
            </div>
          </div>
        </OutlinedCard>
      )}
      {isExpanded && (
        <LearningCard
          card={DUMMY_KOR_CARD_DETAIL}
          cardState={cardState}
          toggleConjugation={toggleConjugation}
          toggleExample={toggleExample}
          menuItems={menu_items}
          setContentHeight={setContentHeight}
        />
      )}
    </div>
  );
};

export default WordList;
