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
import { Paginated, Deck } from '@/types/schemes';
import { getDecks } from '@/api/decks';
import { useToken } from '@/hooks/useToken';

export default function DifficultyPage() {
  const t = useTranslations();
  const locale = useLocale(); // 현재 로케일 가져오기
  const [decks, setDecks] = useState<Paginated<Deck> | null>(null);
  const { token } = useToken();

  useEffect(() => {
    console.log('token:', token);
    if (!token) return;
    const fetchDecks = async () => {
      try {
        const response = await getDecks('level', token);
        console.log('getDecks response:', response);
        if (response && 'message' in response && 'code' in response) {
          console.error('Failed to fetch decks:', response);
        } else {
          setDecks(response as Paginated<Deck>);
        }
      } catch (error) {
        console.error('Failed to fetch decks:', error);
      }
    };
    fetchDecks();
  }, [token]);

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

  if (decks === null) {
    return <div className={styles['page']}>Loading...</div>;
  }

  const CardComponent = isCompact ? DeckCardCompact : DeckCard;

  return (
    <>
      <div className={styles['page']}>
        <div className={styles['content']}>
          {!isCompact && <h1 className={styles.title}> {t('level.wordsByDifficulty')} </h1>}
          <div className={styles.cards}>
            {decks?.content.map((deck, index) => (
              <CardComponent
                key={index}
                locale={locale}
                buttonLabels={buttonLabels}
                deck={deck}
                // TODO
                isCompleted={false}
                onLearn={() => handleLearn(false)}
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
