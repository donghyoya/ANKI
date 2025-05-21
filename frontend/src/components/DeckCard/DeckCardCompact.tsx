'use client';

import React, { useState } from 'react';
import classNames from 'classnames';

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
  title,
  isCompleted,
  locale,
  buttonLabels,
  onLearn,
  onViewWords
}: DeckCardProps) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const handleClick = () => {
    setIsExpanded((prev) => !prev);
  };

  const handleViewWords = (e: Event) => {
    e.stopPropagation();
    onViewWords();
  };

  return (
    <OutlinedCard ripple={false}>
      <div
        className={classNames(styles.card, { [styles['card-expanded']]: isExpanded })}
        onClick={handleClick}
      >
        <div className={styles['main-contents']}>
          <div className={styles['title-container']}>
            <h2 className={styles.title}>{title}</h2>
            {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
          </div>
          <FilledButton onClick={onLearn}>{buttonLabels.learn}</FilledButton>
        </div>
        {isExpanded && (
          <div className={styles['extra-contents']}>
            <TextButton onClick={handleViewWords}>{buttonLabels.viewWords}</TextButton>
            <span className={styles['word-count']}>
              {getFormatUnit(locale, 'word', deck.cardCounts, true)}
            </span>
          </div>
        )}
        <DeckProgressBar deck={deck} isExpanded={isExpanded}></DeckProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCardCompact;
