import { formatDuration } from '@/utils/formatDuration';

import styles from './RatingButton.module.scss';

interface RatingButtonProps {
  label: string;
  interval: number;
}

const RatingButton = ({ label, interval }: RatingButtonProps) => {
  return (
    <button className={styles['rating-button']}>
      <span className={styles['rating-button-text']}>{label}</span>
      <span className={styles['rating-button-text']}>{formatDuration(interval)}</span>
    </button>
  );
};

export default RatingButton;
