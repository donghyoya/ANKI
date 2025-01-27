'use client';

import { useTranslations } from 'next-intl';
import { Link } from '@/i18n/routing';
import dynamic from 'next/dynamic';
import { Suspense } from 'react';

const FilledButton = dynamic(() => import('@/components/material-components/FilledButton'), {
  ssr: false
});

export default function HomePage() {
  const t = useTranslations('HomePage');
  return (
    <div>
      <h1>{t('title')}</h1>
      <Link href="/about">{t('about')}</Link>
      <div>
        <Suspense fallback={<div>Loading...</div>}>
          <FilledButton>Hello</FilledButton>
          {/* examples */}
          <p className="md-typescale-display-large">Display Large</p>
          <p className="md-typescale-display-medium">Display Medium</p>
          <p className="md-typescale-display-small">Display Small</p>
          <p className="md-typescale-headline-large">Headline Large</p>
          <p className="md-typescale-headline-medium">Headline Medium</p>
          <p className="md-typescale-headline-small">Headline Small</p>
          <h1 className="md-typescale-title-large">Title Large</h1>
          <h2 className="md-typescale-title-medium">Title Medium</h2>
          <h3 className="md-typescale-title-small">Title Small</h3>
          <p className="md-typescale-body-large">Body Large</p>
          <p className="md-typescale-body-medium">Body Medium</p>
          <p className="md-typescale-body-small">Body Small</p>
          <p className="md-typescale-label-large">Label Large</p>
          <p className="md-typescale-label-medium">Label Medium</p>
          <p className="md-typescale-label-small">Label Small</p>
        </Suspense>
      </div>
    </div>
  );
}
