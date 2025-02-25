'use client';

import React, { useState, useRef, useEffect, useMemo } from 'react';
import { useTranslations, useLocale } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';

import DeckCard from '@/components/DeckCard/DeckCard';
import DeckCardCompact from '@/components/DeckCard/DeckCardCompact';
import Dialog from '@/components/material-components/Dialog';
import TextButton from '@/components/material-components/TextButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';

import styles from './Difficulty.module.scss';

const difficultyLevels = [
  { name: 'Beginner', level: 'beginner', isCompleted: true, wordCount: 1234 },
  { name: 'Intermediate', level: 'intermediate', isCompleted: false, wordCount: 2345 },
  { name: 'Advanced', level: 'advanced', isCompleted: false, wordCount: 983 }
];

export default function DifficultyPage() {
  const t = useTranslations();
  const locale = useLocale(); // 현재 로케일 가져오기

  const { width } = useWindowSize();
  const isCompact = width < 1200;

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const dialogRef = useRef<typeof Dialog.prototype | null>(null);

  const buttonLabels = useMemo(
    () => ({
      viewWords: t('viewWords'),
      learn: t('learn')
    }),
    [t]
  );

  // 'Dialog' 닫기 이벤트 감지해서 'isDialogOpen' 업데이트
  useEffect(() => {
    const dialog = dialogRef.current;
    if (!dialog) return;

    const handleDialogClosed = () => {
      setIsDialogOpen(false);
    };

    dialog.addEventListener('closed', handleDialogClosed);
    return () => dialog.removeEventListener('closed', handleDialogClosed);
  }, []);

  const handleLearn = (isCompleted: boolean) => {
    if (isCompleted) {
      setIsDialogOpen(true);
    }
  };

  const CardComponent = isCompact ? DeckCardCompact : DeckCard;

  return (
    <>
      <div className={styles['page']}>
        <div className={styles['content']}>
          {!isCompact && <h1 className={styles.title}> {t('level.wordsByDifficulty')} </h1>}
          <div className={styles.cards}>
            {difficultyLevels.map((difficulty) => (
              <CardComponent
                key={difficulty.level}
                title={t(`level.${difficulty.level}`)}
                isCompleted={difficulty.isCompleted}
                wordCount={difficulty.wordCount}
                locale={locale}
                buttonLabels={buttonLabels}
                level={difficulty.level}
                onLearn={() => handleLearn(difficulty.isCompleted)}
                onViewWords={() => {}}
              />
            ))}
          </div>
        </div>
      </div>

      <Dialog ref={dialogRef} open={isDialogOpen} noFocusTrap>
        <span slot="headline">
          <span>{t('goalComplete')}</span>
          <IconButton
            value="close"
            aria-label="Close dialog"
            onClick={() => {
              setIsDialogOpen(false);
              dialogRef.current?.close();
            }}
          >
            <Icon>close</Icon>
          </IconButton>
        </span>
        <form id="form" slot="content" method="dialog">
          {t('continuePrompt')}
          <br />
          {t('chooseOption')}
        </form>
        <div slot="actions">
          <TextButton>{t('learnMore')}</TextButton>
          <TextButton>{t('reviewMore')}</TextButton>
        </div>
      </Dialog>
    </>
  );
}
