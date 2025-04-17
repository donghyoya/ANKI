import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { UserCard } from '@/types/schemes';
import { Progress } from '@/types/Progress';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { useEffect, useMemo } from 'react';

const LearningProgressBar = ({
  userCards,
  className
}: {
  userCards: UserCard[];
  className: string;
}) => {
  const progress: Progress[] = useMemo(
    () => [
      {
        value: userCards.filter((card) => card.state === 'Matured').length || 0,
        label: userCards.filter((card) => card.state === 'Matured').length.toString(),
        tooltip: 'Completed',
        color: LEARNING_PROGRESS_BAR_COLORS.completed
      },
      {
        value: userCards.filter((card) => card.state === 'Learning').length || 0,
        label: userCards.filter((card) => card.state === 'Learning').length.toString(),
        tooltip: 'Learning',
        color: LEARNING_PROGRESS_BAR_COLORS.learning
      },
      {
        value: userCards.filter((card) => card.state === 'Overdue').length || 0,
        label: userCards.filter((card) => card.state === 'Overdue').length.toString(),
        tooltip: 'Overdue',
        color: LEARNING_PROGRESS_BAR_COLORS.overdue
      },
      {
        value: userCards.filter((card) => card.state === 'New').length || 0,
        label: userCards.filter((card) => card.state === 'New').length.toString(),
        tooltip: 'New',
        color: LEARNING_PROGRESS_BAR_COLORS.reviewing
      }
    ],
    [userCards]
  );

  useEffect(() => {
    console.log(progress);
  }, [progress]);

  return (
    <div className={`${styles['container']} ${className}`}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
