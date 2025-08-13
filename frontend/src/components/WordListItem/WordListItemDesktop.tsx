'use client';

import React from 'react';

import { OutlinedCard } from '../Card/Card';

import styles from './WordListItemDesktop.module.scss';
import { WordListItemProps } from './types';
import { useRouter } from 'next/navigation';

const WordListItemDesktop = ({
  KoreanWord,
  ForeignWord,
  isHideKorean,
  isHideForeign,
  homographNumber,
  cardId
}: WordListItemProps) => {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/card/${cardId}`);
  };

  return (
    <OutlinedCard className={styles.card} onClick={handleClick} style={{ minHeight: '56px' }}>
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
  );
};

export default WordListItemDesktop;
