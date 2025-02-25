import { WordInfo } from '@/types/Card';

import styles from './WordSection.module.scss';

interface WordSectionProps {
  wordInfo: WordInfo;
}

const levelStars = {
  beginner: '★★★',
  intermediate: '★★',
  advanced: '★'
};

const WordSection = ({ wordInfo }: WordSectionProps) => {
  const levelLabel = levelStars[wordInfo.level as keyof typeof levelStars] || '';

  return (
    <>
      <div className={styles['korean-container']}>
        <span className={styles['korean-word']}>{wordInfo.koreanWord}</span>
        <span className={styles['korean-homograph-number']}>{wordInfo.homographNumber}</span>
        <div className={styles['korean-info-container']}>
          <span className={styles['korean-level']}>{levelLabel}</span>
          <div className={styles['korean-info-sub-container']}>
            <span className={styles['pronunciation']}>{`[${wordInfo.pronunciation}]`}</span>
            <span className={styles['origin']}>{wordInfo.originalLanguage ?? ''}</span>
          </div>
        </div>
      </div>
      <div className={styles['foreign-container']}>
        <span className={styles['foreign-word']}>
          1. {wordInfo.partsOfSpeech} {wordInfo.foreignWord}
        </span>
        <span className={styles['foreign-word-sub']}>{wordInfo.relatedWords}</span>
      </div>
    </>
  );
};

export default WordSection;
