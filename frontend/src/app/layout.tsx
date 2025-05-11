import { setRequestLocale } from 'next-intl/server';
import { getLocale } from 'next-intl/server';
import { notFound } from 'next/navigation';
import { routing } from '@/i18n/routing';
import type { Metadata } from 'next';
import { ThemeProvider } from '@/context/ThemeContext';
import classNames from 'classnames';
import {
  Noto_Sans_KR,
  Roboto,
  Noto_Sans_Arabic,
  Noto_Sans_JP,
  Noto_Sans_Thai,
  Noto_Sans_SC
} from 'next/font/google';

import './globals.scss';

export const metadata: Metadata = {
  title: 'HADA',
  description: 'HADA'
};

const theme = 'light';

export function generateStaticParams() {
  return routing.locales.map((locale) => ({ locale }));
}

const notoSansKr = Noto_Sans_KR({
  subsets: ['latin'],
  weight: ['100', '300', '400', '500', '700', '900']
});

const roboto = Roboto({
  subsets: ['latin'],
  weight: ['100', '300', '400', '500', '700', '900'],
  style: ['normal', 'italic']
});

const notoSansAr = Noto_Sans_Arabic({
  subsets: ['arabic'],
  weight: ['100', '300', '400', '500', '700', '900']
});

const notoSansJp = Noto_Sans_JP({
  subsets: ['latin'],
  weight: ['100', '300', '400', '500', '700', '900']
});

const notoSansTh = Noto_Sans_Thai({
  subsets: ['latin'],
  weight: ['100', '300', '400', '500', '700', '900']
});

const notoSansSc = Noto_Sans_SC({
  subsets: ['latin'],
  weight: ['100', '300', '400', '500', '700', '900']
});

const fonts = {
  ar: notoSansAr,
  ja: notoSansJp,
  th: notoSansTh,
  zh: notoSansSc
};

export default async function RootLayout({ children }: { children: React.ReactNode }) {
  // 현재 locale 가져오기
  const locale = await getLocale();

  // Ensure that the incoming `locale` is valid
  if (!routing.locales.includes(locale as (typeof routing.locales)[number])) {
    notFound();
  }

  // Enable static rendering
  setRequestLocale(locale);

  return (
    <html lang={locale}>
      <body
        className={classNames(
          'antialiased',
          theme,
          notoSansKr.className,
          roboto.className,
          fonts[locale as keyof typeof fonts]?.className
        )}
      >
        <ThemeProvider>{children}</ThemeProvider>
      </body>
    </html>
  );
}
