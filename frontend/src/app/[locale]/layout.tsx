import { setRequestLocale } from 'next-intl/server';
import { NextIntlClientProvider } from 'next-intl';
import { getLocale, getMessages } from 'next-intl/server';
import { notFound } from 'next/navigation';
import { routing } from '@/i18n/routing';
import type { Metadata } from 'next';
import '../globals.scss';

export const metadata: Metadata = {
  title: 'HADA',
  description: 'HADA'
};

const theme = 'light';

export function generateStaticParams() {
  return routing.locales.map((locale) => ({ locale }));
}

export default async function LocaleLayout({
  children
}: {
  children: React.ReactNode;
}) {
  // 현재 locale 가져오기
  const locale = await getLocale();
  // Providing all messages to the client
  const messages = await getMessages();

  // Ensure that the incoming `locale` is valid
  if (!routing.locales.includes(locale as any)) {
    notFound();
  }

  // Enable static rendering
  setRequestLocale(locale);

  return (
    <html lang={locale}>
      <body className={`antialiased ${theme}`}>
        <NextIntlClientProvider messages={messages} >
          {children}
        </NextIntlClientProvider>
      </body>
    </html>
  );
}
