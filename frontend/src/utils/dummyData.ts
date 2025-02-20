import { Card } from '@/types/Card';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/utils/constants';
import { IntervalPreview } from '@/types/IntervalPreview';
import { MenuItem } from '@/types/Menu';
import { Progress } from '@/types/Progress';

export const DUMMY_CARD: Card = {
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

export const DUMMY_PROGRESS: Progress[] = [
  { value: 10, label: '10', tooltip: 'Completed', color: LEARNING_PROGRESS_BAR_COLORS.completed },
  { value: 20, label: '20', tooltip: 'Learning', color: LEARNING_PROGRESS_BAR_COLORS.learning },
  { value: 30, label: '30', tooltip: 'Reviewing', color: LEARNING_PROGRESS_BAR_COLORS.reviewing }
];

export const DUMMY_RATING_PREVIEW: IntervalPreview = {
  again: new Date(Date.now() + 1 * 60 * 1000),
  hard: new Date(Date.now() + 10 * 60 * 1000),
  good: new Date(Date.now() + 1 * 24 * 60 * 60 * 1000),
  easy: new Date(Date.now() + 1 * 30 * 24 * 60 * 60 * 1000)
};

export const DUMMY_MENU_ITEMS: MenuItem[] = [
  { label: '사과', onClick: () => {} },
  { label: '바나나', onClick: () => {} },
  { label: '오렌지', onClick: () => {} }
];
