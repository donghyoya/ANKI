'use client';

import { useEffect, useState } from 'react';
import { useTranslations } from 'next-intl';
import { useRouter, useSearchParams } from 'next/navigation';
import { redirect } from 'next/navigation';
import { refresh } from '@/api/auth';
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner';
import CustomDialog from '@/components/Dialogs/CustomDialog';

export default function GoogleLoginRedirectPage() {
  // URL에서 임시 토큰 읽기
  const router = useRouter();
  const searchParams = useSearchParams();
  const authenticationToken = searchParams?.get('token');

  const t = useTranslations();
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  // 토큰 저장 로직
  useEffect(() => {
    if (!authenticationToken) {
      setIsDialogOpen(true);
      return;
    }
    (async function () {
      const { isSetup } = await refresh(authenticationToken);
      if (isSetup) {
        redirect('/difficulty');
      } else {
        redirect('/settings');
      }
    })();
  }, [authenticationToken, router]);

  const handleDialog = () => {
    setIsDialogOpen(false);
    router.push('/login');
  };

  return (
    <div>
      <div>
        {authenticationToken ? (
          <LoadingSpinner />
        ) : (
          <CustomDialog
            open={isDialogOpen}
            headline={t('loginError')}
            prompt={t('loginErrorContent')}
            firstButtonString="OK"
            firstButtonOnclick={handleDialog}
          />
        )}
      </div>
    </div>
  );
}
