import { KoreanCardDetail } from '@/types/schemes';

import styles from './WordSection.module.scss';
import { Fragment } from 'react';

const levelStars = {
  easy: '★★★',
  medium: '★★',
  hard: '★'
};

interface WordSectionProps {
  card: KoreanCardDetail;
}

const WordSection = ({ card }: WordSectionProps) => {
  const levelLabel = levelStars[card.level as keyof typeof levelStars] || '';

  return (
    <>
      <div className={styles['korean-container']}>
        <span className={styles['korean-word']}>{card.koreanWord}</span>
        <span className={styles['korean-homograph-number']}>{+card.homographNumber + 1}</span>
        <div className={styles['korean-info-container']}>
          <span className={styles['korean-level']}>{levelLabel}</span>
          <div className={styles['korean-info-sub-container']}>
            <span className={styles['pronunciation']}>{`[${card.meanings[0].pronunciation}]`}</span>
            <span className={styles['origin']}>{card.meanings[0].originalLanguage ?? ''}</span>
          </div>
        </div>
      </div>
      {card.meanings.map((meaning, index) => (
        <Fragment key={`${card.koreanWord}-${index}`}>
          <div className={styles['foreign-container']}>
            <span className={styles['foreign-word']}>
              {index + 1}. {meaning.partsOfSpeech} {meaning.foreignWord}
            </span>
            <span className={styles['foreign-word-sub']}>{meaning.foreignMeaning}</span>
            <span className={styles['foreign-word-sub']}>{meaning.relatedWords}</span>
          </div>
        </Fragment>
      ))}
    </>
  );
};

export default WordSection;
