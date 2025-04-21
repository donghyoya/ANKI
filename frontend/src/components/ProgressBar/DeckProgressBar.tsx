import React from 'react';

import { Deck } from '@/types/schemes';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
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
      color: LEARNING_PROGRESS_BAR_COLORS.matured
    },
    {
      value: overdueCounts,
      label: String(overdueCounts),
      tooltip: 'Overdue',
      color: LEARNING_PROGRESS_BAR_COLORS.overdue
    },
    {
      value: learningCounts,
      label: String(learningCounts),
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.learning
    },
    {
      value: 0,
      label: String(0),
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.new
    }
  ];

  return (
    <ProgressBar progress={progress} styles={stylesProp} className={className} height={height} />
  );
};

export default DeckProgressBar;
