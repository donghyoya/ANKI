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
  const { maturityCounts, overdueCounts, cardCounts } = deck;
  const learningCounts = cardCounts - maturityCounts - overdueCounts;

  useEffect(() => {
    console.log(deck);
  }, [deck]);

  const progress: Progress[] = [
    {
      value: maturityCounts,
      label: isExpanded ? maturityCounts : '',
      tooltip: 'Review',
      color: LEARNING_PROGRESS_BAR_COLORS.Review
    },
    {
      value: learningCounts,
      label: isExpanded ? learningCounts : '',
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.Learning
    },
    {
      value: overdueCounts,
      label: isExpanded ? overdueCounts : '',
      tooltip: 'Relearning',
      color: LEARNING_PROGRESS_BAR_COLORS.Relearning
    },
    {
      value: 0,
      label: isExpanded ? 0 : '',
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.New
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
