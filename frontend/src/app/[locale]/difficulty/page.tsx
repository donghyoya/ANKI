'use client';

import React from 'react';
import DeckCard from '@/components/DeckCard/DeckCard';
import { useTranslations } from 'next-intl';
import { useLocale } from 'next-intl';
import styles from './Difficulty.module.scss';

export default function DifficultyPage() {
  const t = useTranslations();

  const locale = useLocale(); // 현재 로케일 가져오기

  const buttonLabels = {
    viewWords: t('viewWords'),
    learn: t('learn'),
  }

  const handleViewWords = () => {
    alert('Viewing words!');
  };

  const handleLearn = () => {
    alert('Starting learning!');
  };

  return (
    <div className={styles.page}>
      <div className={styles.content}>
        <h1 className={`${styles.title} md-typescale-headline-large`}>
          Words List
        </h1>
        <div className={styles.cards}>
          <DeckCard
            title={t('beginner')}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
          <DeckCard
            title={t('intermediate')}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
          <DeckCard
            title={t('advanced')}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={handleLearn}
          />
        </div>
      </div>
    </div>
  );
}
