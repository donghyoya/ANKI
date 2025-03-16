'use client'; // 클라이언트 컴포넌트로 설정

import React from 'react';
import Link from 'next/link';

import FilledButton from '@/components/material-components/FilledButton';
import TextButton from '@/components/material-components/TextButton';
import { Icon } from '@/components/material-components/IconButton/IconButton';

import { DeckCardProps } from '@/components/DeckCard/types';
import ProgressBar from '@/components/ProgressBar/ProgressBar';
import { OutlinedCard } from '@/components/Card/Card';

import { getFormatUnit } from '@/utils/unitFormatter';
import { DUMMY_PROGRESS } from '@/utils/dummyData';
import styles from './DeckCard.module.scss';

// DeckCard 컴포넌트
const DeckCard = ({
  title,
  isCompleted,
  wordCount,
  locale,
  buttonLabels,
  isDifficulty,
  level,
  category,
  onLearn
}: DeckCardProps) => {
  return (
    <OutlinedCard className={styles.card} ripple={false}>
      <div className={styles.info}>
        <div className={styles['title-container']}>
          <h2 className={styles.title}>{title}</h2>
          {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
        </div>
        <span className={styles['word-count']}>
          {getFormatUnit(locale, 'word', wordCount, true)}
        </span>
      </div>
      <div className={styles['bottom-contents']}>
        <div className={styles['button-container']}>
          <Link href={isDifficulty ? `/difficulty/${level}` : `/meanings/${category}`}>
            <TextButton>{buttonLabels.viewWords}</TextButton>
          </Link>
          <FilledButton onClick={onLearn}>{buttonLabels.learn}</FilledButton>
        </div>
        <ProgressBar progress={DUMMY_PROGRESS} height={12}></ProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCard;
