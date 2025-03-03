import { useLocale, useTranslations } from 'next-intl';
import { formatDuration } from '@/utils/timeFormatter';
import styles from './RatingButton.module.scss';

interface RatingButtonProps {
  label: string;
  interval: number;
  isError?: boolean;
}

const RatingButton = ({ label, interval, isError = false }: RatingButtonProps) => {
  const t = useTranslations();
  const locale = useLocale();

  return (
    <button className={styles['rating-button'] + (isError ? ' ' + styles['error'] : '')}>
      <span className={styles['rating-button-label']}>{t(`learning.${label}`)}</span>
      <span className={styles['rating-button-interval']}>{formatDuration(interval, locale)}</span>
    </button>
  );
};

export default RatingButton;
