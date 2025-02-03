import { WordInfo } from '@/types/Card';

import styles from './WordSection.module.scss';

interface WordSectionProps {
  wordInfo: WordInfo;
}

const WordSection = ({ wordInfo }: WordSectionProps) => {
  return (
    <>
      <div className={styles['korean-container']}>
        <span className="md-typescale-headline-large">{wordInfo.koreanWord}</span>
        <span className="md-typescale-headline-small">{wordInfo.homographNumber}</span>
        <div className={styles['korean-info-container']}>
          <span className="md-typescale-label-small">{wordInfo.level}</span>
          <div className={styles['korean-info-sub-container']}>
            <span className={`${styles['pronunciation']} md-typescale-label-large`}>
              {`[${wordInfo.pronunciation}]`}
            </span>
            <span className={`${styles['origin']} md-typescale-label-large`}>
              {wordInfo.originalLanguage ?? ''}
            </span>
          </div>
        </div>
      </div>
      <div className={styles['foreign-container']}>
        <span className={`${styles['foreign-word']} md-typescale-body-large`}>
          1. {wordInfo.partsOfSpeech} {wordInfo.foreignWord}
        </span>
        <span className={`${styles['foreign-word-sub']} md-typescale-label-medium`}>
          {wordInfo.relatedWords}
        </span>
      </div>
    </>
  );
};

export default WordSection;
