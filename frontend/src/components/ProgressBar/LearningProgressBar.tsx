import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { ProgressBarSegment } from '@/types/ProgressBarSegment';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { StateCounts } from '@/types/study';

const LearningProgressBar = ({ StateCounts }: { StateCounts: StateCounts; className: string }) => {
  const progressBarSegments: ProgressBarSegment[] = [
    {
      value: StateCounts.reviewCounts,
      label: StateCounts.reviewCounts,
      tooltip: 'Completed',
      color: LEARNING_PROGRESS_BAR_COLORS.Review
    },
    {
      value: StateCounts.overdueCounts,
      label: StateCounts.overdueCounts,
      tooltip: 'Review',
      color: LEARNING_PROGRESS_BAR_COLORS.Overdue
    },
    {
      value: StateCounts.newCounts,
      label: StateCounts.newCounts,
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.New
    },
    {
      value: StateCounts.learningCounts,
      label: StateCounts.learningCounts,
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.Learning
    }
  ];

  return (
    <div className={styles['container']}>
      <ProgressBar progressBarSegments={progressBarSegments} height={15} />
    </div>
  );
};

export default LearningProgressBar;
