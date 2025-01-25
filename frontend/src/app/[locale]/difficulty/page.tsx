'use client';

import React from 'react';
import DeckCard from '@/components/DeckCard/DeckCard';
import { useTranslations } from 'next-intl';

export default function DifficultyPage() {
  const t = useTranslations();

  const handleViewWords = () => {
    alert('Viewing words!');
  };

  const handleLearn = () => {
    alert('Starting learning!');
  };

  return (
    <div>
      <DeckCard
        title={t('beginner')}
        wordCount={1234}
        onViewWords={handleViewWords}
        onLearn={handleLearn}
      />
    </div>
  );
}
