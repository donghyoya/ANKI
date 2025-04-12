'use client';

import DeckListPage from '@/components/common/DeckListPage';

import React from 'react';

const difficultyLevels = [
  { name: 'Beginner', key: 'beginner', isCompleted: true, wordCount: 1234 },
  { name: 'Intermediate', key: 'intermediate', isCompleted: false, wordCount: 2345 },
  { name: 'Advanced', key: 'advanced', isCompleted: false, wordCount: 983 }
];

export default function DifficultyPage() {
  return (
    <DeckListPage
      title="difficulty.wordsByDifficulty"
      data={difficultyLevels}
      isDifficulty={true}
    />
  );
}
