import type { Metadata } from 'next';
import './globals.scss';

import Navigation from '@/components/Navigation/Navigation';

export const metadata: Metadata = {
  title: 'HADA',
  description: 'HADA'
};

const theme = 'light';

export default function RootLayout({
  children
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <head>
        <link
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
          rel="stylesheet"
        />
      </head>
      <body className={`antialiased ${theme}`}>
        <Navigation />
        <main className="layout-container-main">{children}</main>
      </body>
    </html>
  );
}
