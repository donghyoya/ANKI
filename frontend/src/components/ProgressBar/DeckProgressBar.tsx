import React, { useEffect } from 'react';

import { Deck } from '@/types/schemes';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { ProgressBarSegment } from '@/types/ProgressBarSegment';
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
  const { newCounts, learningCounts, maturityCounts } = deck;

  useEffect(() => {
    console.log(deck);
  }, [deck]);

  const progressBarSegments: ProgressBarSegment[] = [
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
      value: newCounts,
      label: isExpanded ? newCounts : '',
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.New
    }
  ];

  return (
    <ProgressBar
      progressBarSegments={progressBarSegments}
      styles={stylesProp}
      className={className}
      height={isExpanded ? 12 : 6}
    />
  );
};

export default DeckProgressBar;
