import { capitalize } from '@/utils/capitalize';
import { formatDuration } from '@/utils/formatDuration';

import styles from './RatingButton.module.scss';

interface RatingButtonProps {
  label: string;
  interval: number;
  isError?: boolean;
}

const RatingButton = ({ label, interval, isError = false }: RatingButtonProps) => {
  return (
    <button className={styles['rating-button'] + (isError ? ' ' + styles['error'] : '')}>
      <span className={styles['rating-button-label']}>{capitalize(label)}</span>
      <span className={styles['rating-button-interval']}>{formatDuration(interval)}</span>
    </button>
  );
};

export default RatingButton;
