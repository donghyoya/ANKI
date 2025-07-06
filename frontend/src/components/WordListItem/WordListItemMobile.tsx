'use client';

import React, { useEffect, useState } from 'react';
import { WordListItemProps } from './types';
import { OutlinedCard } from '../Card/Card';
import styles from './WordListItemMobile.module.scss';

const WordListItemMobile = ({
  KoreanWord,
  ForeignWord,
  isExpanded,
  homographNumber
}: WordListItemProps) => {
  const [expanded, setExpanded] = useState(isExpanded || false);

  useEffect(() => {
    if (isExpanded !== undefined) setExpanded(isExpanded);
  }, [isExpanded]);

  const handleClick = () => {
    setExpanded((prev) => !prev);
  };

  return (
    <OutlinedCard
      className={styles.card}
      style={{ minHeight: expanded ? '104px' : '56px' }}
      onClick={handleClick}
    >
      <div className={styles['korean-word']}>
        {KoreanWord}
        <span className={styles['homograph-number']}>{homographNumber}</span>
      </div>
      {expanded && <div className={styles['foreign-word']}>{ForeignWord}</div>}
    </OutlinedCard>
  );
};

export default WordListItemMobile;
