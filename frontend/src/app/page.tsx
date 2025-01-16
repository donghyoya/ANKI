'use client';

import dynamic from 'next/dynamic';
import { Suspense } from 'react';

const FilledButton = dynamic(() => import('@/components/FilledButton'), {
  ssr: false
});

export default function Home() {
  return (
    <div>
      <Suspense fallback={<div>Loading...</div>}>
        <FilledButton>Hello</FilledButton>
      </Suspense>
    </div>
  );
}
