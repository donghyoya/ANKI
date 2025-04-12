import WordListPage from '@/components/common/WordListPage';

const meaningCategories = [
  'action',
  'administration',
  'communication',
  'concept',
  'culture',
  'economy',
  'fashion',
  'feeling',
  'food',
  'grammar',
  'home',
  'hospital',
  'life',
  'living',
  'nature',
  'news',
  'number',
  'personality',
  'politics',
  'relationships',
  'religion',
  'school',
  'science',
  'time',
  'transport',
  'weather',
  'work'
];

export default function MeaningsWordsPage() {
  return <WordListPage wordType="meanings" validKeys={meaningCategories} />;
}
