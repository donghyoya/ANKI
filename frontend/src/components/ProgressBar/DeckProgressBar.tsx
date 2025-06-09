import React, { useEffect } from 'react';

import { Deck } from '@/types/schemes';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { Progress } from '@/types/Progress';
import ProgressBar from './ProgressBar';

interface DeckProgressBarProps {
  deck: Deck;
  styles?: React.CSSProperties;
  className?: string;
  isExpanded?: boolean;
}

const DeckProgressBar = ({
  deck,
  styles: stylesProp,
  className,
  isExpanded
}: DeckProgressBarProps) => {
  const { newCounts, learningCounts, maturityCounts, overdueCounts } = deck;

  useEffect(() => {
    console.log(deck);
  }, [deck]);

  const progress: Progress[] = [
    {
      value: maturityCounts,
      label: isExpanded ? maturityCounts : '',
      tooltip: 'Learned',
      color: LEARNING_PROGRESS_BAR_COLORS.matured
    },
    {
      value: overdueCounts,
      label: isExpanded ? overdueCounts : '',
      tooltip: 'Overdue',
      color: LEARNING_PROGRESS_BAR_COLORS.overdue
    },
    {
      value: learningCounts,
      label: isExpanded ? learningCounts : '',
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.learning
    },
    {
      value: newCounts,
      label: isExpanded ? newCounts : '',
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.new
    }
  ];

  return (
    <ProgressBar
      progress={progress}
      styles={stylesProp}
      className={className}
      height={isExpanded ? 12 : 6}
    />
  );
};

export default DeckProgressBar;
