import RatingButton from './RatingButton';
import { IntervalPreview } from '@/types/IntervalPreview';

import styles from './RatingButtonContainer.module.scss';

interface RatingButtonContainerProps {
  intervalPreview: IntervalPreview;
  isRevealed: boolean;
}

const RatingButtons = ({ intervalPreview, isRevealed }: RatingButtonContainerProps) => {
  return (
    <div className={styles['rating-button-container']}>
      {isRevealed &&
        Object.entries(intervalPreview).map(([label, dueDate]) => (
          <RatingButton
            key={label}
            label={label}
            interval={dueDate - Date.now()}
            isError={label === 'again'}
          />
        ))}
    </div>
  );
};

export default RatingButtons;
