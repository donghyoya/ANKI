import LearningProgressBar from '@/components/ProgressBar/LearningProgressBar';
import LearningCard from '@/components/LearningCard/LearningCard';
import RatingButton from '@/components/LearningCard/RatingButton';

import styles from './layout.module.scss';

import { DUMMY_CARD, DUMMY_PROGRESS, DUMMY_RATING_PREVIEW } from '@/utils/dummyData';

export default function LearningPage() {
  return (
    <div className={styles['learning-page']}>
      <div className={styles['learning-content']}>
        <div className={styles['progress-container']}>
          <div className={styles['progress-title-container']}>
            <span className={styles['progress-title']}>Reviews</span>
          </div>
          <LearningProgressBar progress={DUMMY_PROGRESS} />
        </div>
        <LearningCard card={DUMMY_CARD} />
        <div className={styles['rating-container']}>
          {Object.entries(DUMMY_RATING_PREVIEW).map(([label, dueDate]) => (
            <RatingButton
              key={label}
              label={label}
              interval={dueDate - Date.now()}
              isError={label === 'again'}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
