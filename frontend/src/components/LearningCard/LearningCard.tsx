import { FilledCard } from '../Card/Card';
import { Card } from '@/types/Card';

import styles from './LearningCard.module.scss';
import ConjugationSection from './ConjugationSection';
import ExampleSection from './ExampleSection';
import WordSection from './WordSection';

export interface LearningCardState {
  isRevealed: boolean;
  showDetail: boolean;
  showConjugation: boolean;
  showExample: boolean;
}

interface LearningCardProps {
  card: Card;
  className?: string;
  style?: React.CSSProperties;
  cardState: LearningCardState;
  handleReveal: () => void;
  handleShowDetail: () => void;
  toggleConjugation: () => void;
  toggleExample: () => void;
}

const LearningCard = ({
  card,
  className,
  style,
  cardState,
  handleReveal,
  handleShowDetail,
  toggleConjugation,
  toggleExample
}: LearningCardProps) => {
  return (
    <FilledCard
      className={`${styles['learning-card']} ${className}`}
      ripple={false}
      onClick={handleReveal}
      style={style}
    >
      {!cardState.isRevealed && (
        <div className={styles['content-container']}>
          <span className="md-typescale-headline-large">{card.wordInfo.koreanWord}</span>
          <span className={`md-typescale-headline-small ${styles['revealed']}`}>Check Answer</span>
        </div>
      )}

      {cardState.isRevealed && !cardState.showDetail && (
        <div className={styles['content-container']}>
          <span className="md-typescale-headline-large">{card.wordInfo.koreanWord}</span>
          <span className="md-typescale-headline-small">{card.wordInfo.foreignWord}</span>
        </div>
      )}

      {cardState.isRevealed && cardState.showDetail && (
        <div className={`${styles['content-container']} ${styles['detailed']}`}>
          <WordSection wordInfo={card.wordInfo} />
          <ConjugationSection
            conjugations={card.wordInfo.inflection}
            toggleExpanded={toggleConjugation}
            isExpanded={cardState.showConjugation}
          />
          <ExampleSection
            examples={card.example}
            toggleExpanded={toggleExample}
            isExpanded={cardState.showExample}
          />
        </div>
      )}
    </FilledCard>
  );
};

export default LearningCard;
