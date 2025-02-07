'use client';

import React, { useState, useLayoutEffect, useRef, useEffect } from 'react';
import DeckCard from '@/components/DeckCard/DeckCard';
import DeckCardCompact from '@/components/DeckCard/DeckCardCompact';
import { useTranslations } from 'next-intl';
import { useLocale } from 'next-intl';
import styles from './Difficulty.module.scss';
import Dialog from '@/components/material-components/Dialog';
import TextButton from '@/components/material-components/TextButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';

export default function DifficultyPage() {
  const t = useTranslations();
  const locale = useLocale(); // 현재 로케일 가져오기

  const [isCompact, setIsCompact] = useState(false);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const dialogRef = useRef<typeof Dialog.prototype | null>(null);

  // 화면 크기 감지해서 'isCompact' 설정
  useLayoutEffect(() => {
    const handleResize = () => {
      setIsCompact(window.innerWidth < 1200);
    };

    handleResize(); // 초기 실행
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

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

  const buttonLabels = {
    viewWords: t('viewWords'),
    learn: t('learn'),
  }

  const handleViewWords = () => {
    alert('Viewing words!');
  };

  const handleLearn = (isCompleted: boolean) => {
    if (isCompleted) {
      setIsDialogOpen(true);
    }
  };

  const CardComponent = isCompact ? DeckCardCompact : DeckCard;


  return (
    <div className={styles.page}>
      <div className={styles.content}>
        {!isCompact && <h1 className={styles.title}>
          {t('wordsByDifficulty')}
        </h1>}
        <div className={styles.cards}>
          <CardComponent
            title={t('beginner')}
            isCompleted={true}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={() => handleLearn(true)}
          />
          <CardComponent
            title={t('intermediate')}
            isCompleted={false}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={() => handleLearn(false)}
          />
          <CardComponent
            title={t('advanced')}
            isCompleted={false}
            wordCount={1234}
            locale={locale}
            buttonLabels={buttonLabels}
            onViewWords={handleViewWords}
            onLearn={() => handleLearn(false)}
          />
        </div>
      </div>

      <Dialog
        ref={dialogRef}
        open={isDialogOpen}
        noFocusTrap
      >
        <span slot="headline" >
          <span>Daily goal completed!</span>
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
          Want to keep going?<br />
          Choose an option below:
        </form>
        <div slot="actions">
          <TextButton>Learn more</TextButton>
          <TextButton>Review more</TextButton>
        </div>
      </Dialog>
    </div>
  );
}
