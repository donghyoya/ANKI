import RatingButton from './RatingButton';
import { IntervalPreview, Rating } from '@/types/IntervalPreview';

import styles from './RatingButtonContainer.module.scss';

interface RatingButtonContainerProps {
  intervalPreview: IntervalPreview;
  isRevealed: boolean;
  onRepeat: (label: Rating) => void;
}

const RatingButtons = ({ intervalPreview, isRevealed, onRepeat }: RatingButtonContainerProps) => {
  return (
    <div className={styles['rating-button-container']}>
      {isRevealed &&
        Object.entries(intervalPreview).map(([label, dueDate]) => (
          <RatingButton
            key={label}
            label={label}
            interval={dueDate - Date.now()}
            isError={label === 'again'}
            onClick={() => onRepeat(label as Rating)}
          />
        ))}
    </div>
  );
};

export default RatingButtons;
