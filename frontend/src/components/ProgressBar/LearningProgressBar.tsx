import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { UserCard } from '@/types/schemes';
import { Progress } from '@/types/Progress';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/utils/constants';

const LearningProgressBar = ({
  userCards,
  className
}: {
  userCards: UserCard[];
  className: string;
}) => {
  const progress: Progress[] = [
    {
      value: userCards.filter((card) => card.state === 'matured').length || 0,
      label: userCards.filter((card) => card.state === 'matured').length.toString(),
      tooltip: 'Completed',
      color: LEARNING_PROGRESS_BAR_COLORS.completed
    },
    {
      value: userCards.filter((card) => card.state === 'learning').length || 0,
      label: userCards.filter((card) => card.state === 'learning').length.toString(),
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.learning
    },
    {
      value: userCards.filter((card) => card.state === 'overdue').length || 0,
      label: userCards.filter((card) => card.state === 'overdue').length.toString(),
      tooltip: 'Overdue',
      color: LEARNING_PROGRESS_BAR_COLORS.overdue
    },
    {
      value: userCards.filter((card) => card.state === 'new').length || 0,
      label: userCards.filter((card) => card.state === 'new').length.toString(),
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.reviewing
    }
  ];

  return (
    <div className={`${styles['container']} ${className}`}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
