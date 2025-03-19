'use client';

import WordListPage from '@/components/common/WordListPage';

const difficultyLevels = ['beginner', 'intermediate', 'advanced'];

export default function DifficultyWordsPage() {
  return <WordListPage wordType="difficulty" validKeys={difficultyLevels} />;
}
