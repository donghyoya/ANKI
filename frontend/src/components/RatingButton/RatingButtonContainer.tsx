import RatingButton from './RatingButton';
import { Rating } from 'ts-fsrs';
import { IPreview, ReviewLog } from '@/types/fsrs';

import styles from './RatingButtonContainer.module.scss';

interface RatingButtonContainerProps {
  iPreview: IPreview | null;
  isRevealed: boolean;
  onRepeat: (label: Rating) => void;
}

const RatingButtons = ({ iPreview, isRevealed, onRepeat }: RatingButtonContainerProps) => {
  const ratings = {
    Again: Rating.Again,
    Hard: Rating.Hard,
    Good: Rating.Good,
    Easy: Rating.Easy
  } as const;

  const logs: ReviewLog[] = iPreview ? Object.values(iPreview).map((item) => item.log) : [];

  return (
    <div className={styles['rating-button-container']}>
      {isRevealed &&
        logs.map((log, index) => {
          if (!log) return null;
          const interval = log.due.getTime() - new Date().getTime();
          return (
            <RatingButton
              key={index}
              label={Object.keys(ratings)[index]}
              interval={interval}
              onClick={() => onRepeat(Object.values(ratings)[index])}
              isError={!index}
            />
          );
        })}
    </div>
  );
};

export default RatingButtons;
