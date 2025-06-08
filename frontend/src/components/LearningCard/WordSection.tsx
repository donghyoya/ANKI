import { CardDetail } from '@/types/schemes';

import styles from './WordSection.module.scss';

const levelStars = {
  beginner: '★★★',
  intermediate: '★★',
  advanced: '★'
};

interface WordSectionProps {
  card: CardDetail;
}

const WordSection = ({ card }: WordSectionProps) => {
  const levelLabel = levelStars[card.difficulty as keyof typeof levelStars] || '';

  return (
    <>
      <div className={styles['korean-container']}>
        <span className={styles['korean-word']}>{card.koreanWord}</span>
        <span className={styles['korean-homograph-number']}>{card.homographNumber}</span>
        <div className={styles['korean-info-container']}>
          <span className={styles['korean-level']}>{levelLabel}</span>
          <div className={styles['korean-info-sub-container']}>
            <span className={styles['pronunciation']}>{`[${card.pronunciation}]`}</span>
            <span className={styles['origin']}>{card.originalLanguage ?? ''}</span>
          </div>
        </div>
      </div>
      <div className={styles['foreign-container']}>
        <span className={styles['foreign-word']}>
          1. {card.partsOfSpeech} {card.foreignWord}
        </span>
        <span className={styles['foreign-word-sub']}>{card.relatedWords}</span>
      </div>
    </>
  );
};

export default WordSection;
