'use client';

import React from 'react';
import DeckCard from '@/components/DeckCard/DeckCard';
import { useTranslations } from 'next-intl';
import styles from './Difficulty.module.scss'

export default function DifficultyPage() {
  const t = useTranslations();

  const handleViewWords = () => {
    alert('Viewing words!');
  };

  const handleLearn = () => {
    alert('Starting learning!');
  };

  const buttonLabels = {
    viewWords: t('viewWords'),
    learn: t('learn'),
  }

  return (
    <div className={styles.page}>
      <div className={styles.content}>
        <div className={`${styles.title} md-typescale-headline-large`}>
          Words List
        </div>
        <div className={styles.cards}>
          <DeckCard
            title={t('beginner')}
            wordCount={1234}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
          <DeckCard
            title={t('intermediate')}
            wordCount={1234}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
          <DeckCard
            title={t('advanced')}
            wordCount={1234}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
        </div>
      </div>
    </div>
  );
}
