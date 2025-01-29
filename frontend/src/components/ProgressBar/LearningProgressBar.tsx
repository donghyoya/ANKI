import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { LEARNING_PROGRESS_BAR_COLORS } from '@/utils/constants';

const LearningProgressBar = () => {
  const bars = [
    { value: 10, label: '10', tooltip: 'Learning', color: LEARNING_PROGRESS_BAR_COLORS.learning },
    { value: 20, label: '20', tooltip: 'Reviewing', color: LEARNING_PROGRESS_BAR_COLORS.reviewing },
    { value: 30, label: '30', tooltip: 'Completed', color: LEARNING_PROGRESS_BAR_COLORS.completed }
  ];

  return (
    <div className={styles['container']}>
      <ProgressBar bars={bars} height={15} />
    </div>
  );
};

export default LearningProgressBar;
