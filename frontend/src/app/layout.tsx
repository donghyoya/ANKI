import { setRequestLocale } from 'next-intl/server';
import { getLocale } from 'next-intl/server';
import { notFound } from 'next/navigation';
import { routing } from '@/i18n/routing';
import type { Metadata } from 'next';

import './globals.scss';

export const metadata: Metadata = {
  title: 'HADA',
  description: 'HADA'
};

const theme = 'light';

export function generateStaticParams() {
  return routing.locales.map((locale) => ({ locale }));
}

const fontLinks: Record<string, string> = {
  ar: 'https://fonts.googleapis.com/css2?family=Noto+Sans+Arabic:wght@100..900&display=swap',
  ja: 'https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap',
  th: 'https://fonts.googleapis.com/css2?family=Noto+Sans+Thai:wght@100..900&display=swap',
  zh: 'https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@100..900&display=swap',
};

export default async function RootLayout({ children }: { children: React.ReactNode }) {
  // 현재 locale 가져오기
  const locale = await getLocale();
  const extraFont = fontLinks[locale] || "";

  // Ensure that the incoming `locale` is valid
  if (!routing.locales.includes(locale as any)) {
    notFound();
  }

  // Enable static rendering
  setRequestLocale(locale);

  return (
    <html lang={locale}>
      <head>
        <link
          href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet"
        />
        <link
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&display=swap"
          rel="stylesheet"
        />
        {extraFont && <link href={extraFont} rel="stylesheet" />}
      </head>
      <body className={`antialiased ${theme}`}>{children}</body>
    </html>
  );
}
