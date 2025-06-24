import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { ProgressBarSegment } from '@/types/ProgressBarSegment';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { StudyCounts } from '@/services/StudyService';

const LearningProgressBar = ({ studyCounts }: { studyCounts: StudyCounts; className: string }) => {
  const progressBarSegments: ProgressBarSegment[] = [
    {
      value: studyCounts.reviewCounts,
      label: studyCounts.reviewCounts,
      tooltip: 'Completed',
      color: LEARNING_PROGRESS_BAR_COLORS.Review
    },
    {
      value: studyCounts.overdueCounts,
      label: studyCounts.overdueCounts,
      tooltip: 'Review',
      color: LEARNING_PROGRESS_BAR_COLORS.Overdue
    },
    {
      value: studyCounts.newCounts,
      label: studyCounts.newCounts,
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.New
    },
    {
      value: studyCounts.learningCounts,
      label: studyCounts.learningCounts,
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
