import ProgressBar from './ProgressBar';

import styles from './LearningProgressBar.module.scss';

import { Progress } from '@/types/Progress';

const LearningProgressBar = ({
  progress,
  className
}: {
  progress: Progress[];
  className: string;
}) => {
  return (
    <div className={`${styles['container']} ${className}`}>
      <ProgressBar progress={progress} height={15} />
    </div>
  );
};

export default LearningProgressBar;
