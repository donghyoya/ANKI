'use client';

import React, { useState, useMemo } from 'react';
import { useTranslations, useLocale } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';
import { useRouter } from 'next/navigation';

import DeckCardDesktop from '@/components/DeckCard/DeckCard';
import DeckCardCompact from '@/components/DeckCard/DeckCardCompact';
import CustomDialog from '@/components/Dialogs/CustomDialog';

import styles from './DeckListPage.module.scss';
import { Deck } from '@/types/schemes';
import { Category, CategoryType } from '@/types/Category';
import { camelize } from 'humps';

export default function DeckListPage({
  decks,
  categoryType,
  displayOrder
}: {
  decks: Deck[];
  categoryType: CategoryType;
  displayOrder: Category[];
}) {
  const t = useTranslations();
  const locale = useLocale();
  const router = useRouter();

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

  const handleViewWords = (deck: Deck) => {
    router.push(`/${locale}/${categoryType}/${deck.category}`);
  };

  const DeckCard = isCompact ? DeckCardCompact : DeckCardDesktop;

  const sortedDecks = useMemo(() => {
    return decks.sort(
      (a, b) => displayOrder.indexOf(a.category) - displayOrder.indexOf(b.category)
    );
  }, [decks, displayOrder]);

  const getTitle =
    categoryType === 'difficulty'
      ? (category: string) => `difficulty.${category}`
      : (category: string) => `meaning.${camelize(category.toLowerCase())}`;

  return (
    <>
      <div className={styles['page']}>
        <div className={styles['content']}>
          {!isCompact && (
            <h1 className={styles.title}>
              {t(
                `${categoryType}.wordsBy${categoryType.charAt(0).toUpperCase() + categoryType.slice(1)}`
              )}
            </h1>
          )}
          <div className={styles.cards}>
            {sortedDecks.map((deck) => (
              <DeckCard
                key={deck.category}
                deck={deck}
                title={t(getTitle(deck.category))}
                isCompleted={false} // TODO
                locale={locale}
                buttonLabels={buttonLabels}
                onLearn={() => handleLearn(false)}
                onViewWords={() => handleViewWords(deck)}
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
