'use client'; // 클라이언트 컴포넌트로 설정

import React from 'react';
import Link from 'next/link';

import FilledButton from '@/components/material-components/FilledButton';
import TextButton from '@/components/material-components/TextButton';
import { Icon } from '@/components/material-components/IconButton/IconButton';

import { DeckCardProps } from '@/components/DeckCard/types';

import { getFormatUnit } from '@/utils/unitFormatter';
import styles from './DeckCard.module.scss';
import { OutlinedCard } from '@/components/Card/Card';
import DeckProgressBar from '@/components/ProgressBar/DeckProgressBar';

// DeckCard 컴포넌트
const DeckCard = ({
  deck,
  title,
  isCompleted,
  wordCount,
  locale,
  buttonLabels,
  isDifficulty,
  level,
  onLearn
}: DeckCardProps) => {
  console.log(level);

  return (
    <OutlinedCard className={styles.card} ripple={false}>
      <div className={styles.info}>
        <div className={styles['title-container']}>
          <h2 className={styles.title}>{deck.category}</h2>
          {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
        </div>
        <span className={styles['word-count']}>
          {getFormatUnit(locale, 'word', wordCount, true)}
        </span>
      </div>
      <div className={styles['bottom-contents']}>
        <div className={styles['button-container']}>
          <Link href={isDifficulty ? `/difficulty/${level}` : `/meanings/${deck.category}`}>
            <TextButton>{buttonLabels.viewWords}</TextButton>
          </Link>
          <FilledButton onClick={onLearn}>{buttonLabels.learn}</FilledButton>
        </div>
        <DeckProgressBar deck={deck} height={12}></DeckProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCard;
