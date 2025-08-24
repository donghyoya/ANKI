// app/[locale]/difficulty/ClientPage.tsx
'use client';

import { useEffect, useState } from 'react';
import { useTranslations } from 'next-intl';
import DeckListPage from '@/components/common/DeckListPage';
import { getDecks } from '@/api/decks';
import { difficultiesInDisplayOrder } from '@/types/Category';
import { Deck } from '@/types/schemes';
import CustomDialog from '@/components/Dialogs/CustomDialog';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';

export default function DifficultyClientPage({ isLoggedIn }: { isLoggedIn: boolean }) {
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
    if (!cookieConsent && isLoggedIn) {
      setIsCookieConsentOpen(true);
    }
  }, [isLoggedIn]);

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
        firstButtonString="OK"
        firstButtonOnclick={handleCookieConsent}
      />
    </>
  );
}
