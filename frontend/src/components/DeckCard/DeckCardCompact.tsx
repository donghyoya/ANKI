'use client';

import React, { useState } from 'react';
import Link from 'next/link';
import classnames from 'classnames';

import FilledButton from '@/components/material-components/FilledButton';
import TextButton from '@/components/material-components/TextButton';
import { Icon } from '@/components/material-components/IconButton/IconButton';

import { DeckCardProps } from '@/components/DeckCard/types';
import DeckProgressBar from '@/components/ProgressBar/DeckProgressBar';
import { OutlinedCard } from '@/components/Card/Card';

import { getFormatUnit } from '@/utils/unitFormatter';
import styles from './DeckCardCompact.module.scss';

const DeckCardCompact = ({
  deck,
  isCompleted,
  locale,
  buttonLabels,
  wordCount,
  isDifficulty,
  level,
  category,
  onLearn
}: DeckCardProps) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const handleClick = () => {
    setIsExpanded((prev) => !prev);
  };

  return (
    <OutlinedCard ripple={false}>
      <div
        className={classnames(styles.card, { [styles['card-expanded']]: isExpanded })}
        onClick={handleClick}
      >
        <div className={styles['main-contents']}>
          <div className={styles['title-container']}>
            <h2 className={styles.title}>{deck.category}</h2>
            {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
          </div>
          <FilledButton
            onClick={(e) => {
              e.stopPropagation();
              onLearn();
            }}
          >
            {buttonLabels.learn}
          </FilledButton>
        </div>
        {isExpanded && (
          <div className={styles['extra-contents']}>
            <Link href={isDifficulty ? `/difficulty/${level}` : `/meanings/${category}`}>
              <TextButton onClick={(e) => e.stopPropagation()}>{buttonLabels.viewWords}</TextButton>
            </Link>
            <span className={styles['word-count']}>
              {getFormatUnit(locale, 'word', wordCount, true)}
            </span>
          </div>
        )}
        <DeckProgressBar deck={deck} height={isExpanded ? 12 : 6}></DeckProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCardCompact;
