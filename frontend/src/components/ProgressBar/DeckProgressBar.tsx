import React from 'react';

import { Deck } from '@/types/schemes';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/utils/constants';
import { Progress } from '@/types/Progress';
import ProgressBar from './ProgressBar';

interface DeckProgressBarProps {
  deck: Deck;
  styles?: React.CSSProperties;
  className?: string;
  height: number;
}

const DeckProgressBar = ({ deck, styles: stylesProp, className, height }: DeckProgressBarProps) => {
  const { maturityCounts, overdueCounts, cardCounts } = deck;
  const learningCounts = cardCounts - maturityCounts - overdueCounts;

  const progress: Progress[] = [
    {
      value: maturityCounts,
      label: String(maturityCounts),
      tooltip: 'Learned',
      color: LEARNING_PROGRESS_BAR_COLORS.completed
    },
    {
      value: overdueCounts,
      label: String(overdueCounts),
      tooltip: 'Overdue',
      color: LEARNING_PROGRESS_BAR_COLORS.learning
    },
    {
      value: learningCounts,
      label: String(learningCounts),
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.reviewing
    }
  ];

  return (
    <ProgressBar progress={progress} styles={stylesProp} className={className} height={height} />
  );
};

export default DeckProgressBar;
