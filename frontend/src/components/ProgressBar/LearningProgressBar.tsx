import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { UserCard } from '@/types/schemes';
import { Progress } from '@/types/Progress';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';
import { useEffect, useMemo } from 'react';
import { State } from 'ts-fsrs';

const LearningProgressBar = ({ userCards }: { userCards: UserCard[]; className: string }) => {
  const progress: Progress[] = useMemo(
    () => [
      {
        value: userCards.filter((card) => card.studyInfo.state === State.Review).length || 0,
        label: userCards.filter((card) => card.studyInfo.state === State.Review).length,
        tooltip: 'Matured',
        color: LEARNING_PROGRESS_BAR_COLORS.matured
      },
      {
        value: userCards.filter((card) => card.studyInfo.state === State.Learning).length || 0,
        label: userCards.filter((card) => card.studyInfo.state === State.Learning).length,
        tooltip: 'Learning',
        color: LEARNING_PROGRESS_BAR_COLORS.learning
      },
      {
        value: userCards.filter((card) => card.studyInfo.state === State.Review).length || 0,
        label: userCards.filter((card) => card.studyInfo.state === State.Review).length,
        tooltip: 'Overdue',
        color: LEARNING_PROGRESS_BAR_COLORS.overdue
      },
      {
        value: userCards.filter((card) => card.studyInfo.state === State.New).length || 0,
        label: userCards.filter((card) => card.studyInfo.state === State.New).length,
        tooltip: 'New',
        color: LEARNING_PROGRESS_BAR_COLORS.new
      }
    ],
    [userCards]
  );

  useEffect(() => {
    console.log(progress);
  }, [progress]);

  return (
    <div className={styles['container']}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
