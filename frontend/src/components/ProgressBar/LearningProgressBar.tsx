import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { UserCard } from '@/types/schemes';
import { Progress } from '@/types/Progress';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { State } from 'ts-fsrs';

const LearningProgressBar = ({ userCards }: { userCards: UserCard[]; className: string }) => {
  const reviewCounts = userCards.filter(
    (card) => card.studyInfo.state === State.Review && card.studyInfo.due >= new Date()
  ).length;
  const learningCounts = userCards.filter(
    (card) => card.studyInfo.state === State.Learning || card.studyInfo.state === State.Relearning
  ).length;
  const overdueCounts = userCards.filter(
    (card) => card.studyInfo.state === State.Review && card.studyInfo.due < new Date()
  ).length;
  const newCounts = userCards.filter((card) => card.studyInfo.state === State.New).length;

  const progress: Progress[] = [
    {
      value: reviewCounts,
      label: reviewCounts,
      tooltip: 'Completed',
      color: LEARNING_PROGRESS_BAR_COLORS.Review
    },
    {
      value: overdueCounts,
      label: overdueCounts,
      tooltip: 'Review',
      color: LEARNING_PROGRESS_BAR_COLORS.Overdue
    },
    {
      value: newCounts,
      label: newCounts,
      tooltip: 'New',
      color: LEARNING_PROGRESS_BAR_COLORS.New
    },
    {
      value: learningCounts,
      label: learningCounts,
      tooltip: 'Learning',
      color: LEARNING_PROGRESS_BAR_COLORS.Learning
    }
  ];

  return (
    <div className={styles['container']}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
