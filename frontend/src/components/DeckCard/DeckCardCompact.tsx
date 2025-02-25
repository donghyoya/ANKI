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
import Link from 'next/link';
import { Progress } from '@/types/Progress';

const DeckCardCompact = ({
  title,
  isCompleted,
  wordCount,
  locale,
  buttonLabels,
  level,
  onViewWords,
  onLearn
}: DeckCardProps) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const handleClick = () => {
    setIsExpanded((prev) => !prev);
  };

  console.log('isExpanded', isExpanded);

  const progress = DUMMY_PROGRESS.reduce((acc, curr) => {
    acc.push({ ...curr, label: isExpanded ? curr.label : '' });
    return acc;
  }, [] as Progress[]);

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
            <Link href={`/difficulty/${level}`}>
              <TextButton
                onClick={(e) => {
                  e.stopPropagation();
                  onViewWords();
                }}
              >
                {buttonLabels.viewWords}
              </TextButton>
            </Link>
            <span className={styles['word-count']}>{wordCount.toLocaleString(locale)} words</span>
          </div>
        )}
        <ProgressBar progress={progress} height={isExpanded ? 12 : 6}></ProgressBar>
      </div>
    </OutlinedCard>
  );
};

export default DeckCardCompact;
