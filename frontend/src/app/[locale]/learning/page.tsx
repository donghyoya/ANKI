import LearningCard from '@/components/Card/LearningCard/LearningCard';
import { Card } from '@/types/Card';

import styles from './layout.module.scss';

const DUMMY_CARD: Card = {
  cardId: 1,
  wordInfo: {
    koreanWord: '가깝다',
    foreignWord: 'near; close; adjacent',
    level: 'Beginner',
    languageCode: 'ko',
    originalLanguage: '家具',
    homographNumber: 1,
    partsOfSpeech: '(adj.)',
    pronunciation: '가깝따',
    relatedWords: '반댓말 멀다2 반댓말 멀다2, 멀다2',
    inflection: ['가까운', '가꾸어(가꿔)', '가까우니', '가깝습니다']
  },
  example: {
    phrase: ['안녕하세요', '안녕하세요', '안녕하세요'],
    sentence: ['안녕하세요', '안녕하세요', '안녕하세요'],
    conversation: ['안녕하세요', '안녕하세요', '안녕하세요']
  }
};

export default function LearningPage() {
  return (
    <div className={styles['learning-page']}>
      <LearningCard card={DUMMY_CARD} />
    </div>
  );
}
