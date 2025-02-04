import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { Progress } from '@/types/Progress';

const LearningProgressBar = ({ progress }: { progress: Progress[] }) => {
  return (
    <div className={styles['container']}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
