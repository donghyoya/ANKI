'use client';

import React, { useState, useMemo } from 'react';
import { useTranslations, useLocale } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';

import DeckCard from '@/components/DeckCard/DeckCard';
import DeckCardCompact from '@/components/DeckCard/DeckCardCompact';
import CustomDialog from '@/components/Dialogs/CustomDialog';

import styles from './DeckListPage.module.scss';

interface DeckListPageProps {
  title: string;
  data: Array<{ name: string; key: string; isCompleted: boolean; wordCount: number }>;
  isDifficulty: boolean;
}

export default function DeckListPage({ title, data, isDifficulty }: DeckListPageProps) {
  const t = useTranslations();
  const locale = useLocale(); // 현재 로케일 가져오기

  const { width } = useWindowSize();
  const isCompact = width < 1200;

  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const buttonLabels = useMemo(
    () => ({
      viewWords: t('viewWords'),
      learn: t('learn')
    }),
    [t]
  );

  const handleLearn = (isCompleted: boolean) => setIsDialogOpen(isCompleted);

  const CardComponent = isCompact ? DeckCardCompact : DeckCard;

  return (
    <>
      <div className={styles['page']}>
        <div className={styles['content']}>
          {!isCompact && <h1 className={styles.title}>{t(title)}</h1>}
          <div className={styles.cards}>
            {data.map((item) => (
              <CardComponent
                key={item.key}
                title={t(`${isDifficulty ? 'difficulty' : 'meanings'}.${item.key}`)}
                isCompleted={item.isCompleted}
                wordCount={item.wordCount}
                locale={locale}
                buttonLabels={buttonLabels}
                isDifficulty={isDifficulty}
                level={isDifficulty ? item.key : undefined}
                category={!isDifficulty ? item.key : undefined}
                onLearn={() => handleLearn(item.isCompleted)}
                onViewWords={() => {}}
              />
            ))}
          </div>
        </div>
      </div>
      <CustomDialog
        open={isDialogOpen}
        headline={t('goalComplete')}
        prompt={
          <>
            {t('continuePrompt')}
            <br />
            {t('chooseOption')}
          </>
        }
        firstButtonString={t('learnMore')}
        secondButtonString={t('reviewMore')}
        onCancel={() => setIsDialogOpen(false)}
      />
    </>
  );
}
