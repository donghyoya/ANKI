import { useTranslations } from 'next-intl';
import { formatDuration } from '@/utils/formatDuration';
import styles from './RatingButton.module.scss';

interface RatingButtonProps {
  label: string;
  interval: number;
  isError?: boolean;
  onClick: () => void;
}

const RatingButton = ({ label, interval, isError = false, onClick }: RatingButtonProps) => {
  const t = useTranslations();

  return (
    <button
      className={styles['rating-button'] + (isError ? ' ' + styles['error'] : '')}
      onClick={onClick}
    >
      <span className={styles['rating-button-label']}>{t(`learning.${label}`)}</span>
      <span className={styles['rating-button-interval']}>{formatDuration(interval)}</span>
    </button>
  );
};

export default RatingButton;
