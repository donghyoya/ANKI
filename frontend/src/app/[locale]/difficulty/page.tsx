'use client';

import React, { useEffect, useState } from 'react';
import { useTranslations } from 'next-intl';

import DeckListPage from '@/components/common/DeckListPage';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';
import { getDecks } from '@/api/decks';

import { Deck } from '@/types/schemes';
import { difficultiesInDisplayOrder } from '@/types/Category';
import CustomDialog from '@/components/Dialogs/CustomDialog';

export default function DifficultyPage() {
  const t = useTranslations();
  const [decks, setDecks] = useState<Deck[]>();
  const [isCookieConsentOpen, setIsCookieConsentOpen] = useState(false);

  useEffect(() => {
    const fetchUserCards = async () => {
      const fetchedDecks = await getDecks('difficulty');
      if (fetchedDecks) {
        setDecks(fetchedDecks.content);
      }
    };
    fetchUserCards();

    const cookieConsent = localStorage.getItem('cookieConsent');
    if (!cookieConsent) {
      setIsCookieConsentOpen(true);
    }
  }, []);

  const handleCookieConsent = () => {
    localStorage.setItem('cookieConsent', 'true');
    setIsCookieConsentOpen(false);
  };

  if (!decks) {
    return <LoadingSpinner />;
  }

  return (
    <>
      <DeckListPage
        decks={decks}
        categoryType="difficulty"
        displayOrder={difficultiesInDisplayOrder}
      />
      <CustomDialog
        open={isCookieConsentOpen}
        headline={t('cookies')}
        prompt={t('cookieConsent')}
        firstButtonString={t('ok')}
        firstButtonOnclick={handleCookieConsent}
      />
    </>
  );
}
