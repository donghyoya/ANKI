'use client';

import React, { useState } from 'react';
import styles from './DeckCardCompact.module.scss';
import { DeckCardProps } from '@/components/DeckCard/types';
import FilledButton from '@/components/material-components/FilledButton';
import TextButton from '@/components/material-components/TextButton';
import { Icon } from '@/components/material-components/IconButton/IconButton';
import { OutlinedCard } from '@/components/Card/Card';
import ProgressBar from '@/components/ProgressBar/ProgressBar';
import classnames from 'classnames';
import { DUMMY_PROGRESS } from '@/utils/dummyData';

const DeckCardCompact = ({
  title,
  isCompleted,
  wordCount,
  locale,
  buttonLabels,
  onViewWords,
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
            <h2 className={styles.title}>{title}</h2>
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
            <TextButton
              onClick={(e) => {
                e.stopPropagation();
                onViewWords();
              }}
            >
              {buttonLabels.viewWords}
            </TextButton>
            <span className={styles['word-count']}>{wordCount.toLocaleString(locale)} words</span>
          </div>
        )}
        <ProgressBar progress={DUMMY_PROGRESS} height={isExpanded ? 12 : 6}></ProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCardCompact;
