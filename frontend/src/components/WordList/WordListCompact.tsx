'use client';

import React, { useEffect, useState } from 'react';
import { WordListProps } from './types';
import { OutlinedCard } from '../Card/Card';
import styles from './WordListCompact.module.scss';

const wordListCompact = ({ KoreanWord, ForeignWord, isExpanded }: WordListProps) => {
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
      style={{ height: expanded ? '104px' : '56px' }}
      onClick={handleClick}
    >
      <div className={styles['korean-word']}>{KoreanWord}</div>
      {expanded && <div className={styles['foreign-word']}>{ForeignWord}</div>}
    </OutlinedCard>
  );
};

export default wordListCompact;
