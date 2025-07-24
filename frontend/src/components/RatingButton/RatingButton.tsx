import { useLocale, useTranslations } from 'next-intl';
import { formatDuration } from '@/utils/timeFormatter';
import styles from './RatingButton.module.scss';
import classNames from 'classnames';

interface RatingButtonProps {
  label: string;
  interval: number;
  isError?: boolean;
  onClick: () => void;
}

const RatingButton = ({ label, interval, isError = false, onClick }: RatingButtonProps) => {
  const t = useTranslations();
  const locale = useLocale();

  return (
    <button
      className={classNames(styles['rating-button'], { [styles['error']]: isError })}
      onClick={onClick}
    >
      <span className={styles['rating-button-label']}>{t(`learning.${label.toLowerCase()}`)}</span>
      <span className={styles['rating-button-interval']}>{formatDuration(interval, locale)}</span>
    </button>
  );
};

export default RatingButton;
