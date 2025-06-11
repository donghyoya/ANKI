'use client';

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

import { ErrorFallback } from '@/components/ErrorFallback';

import { AxiosError } from 'axios';

export default function Error({
  error,
  reset
}: {
  error: Error & { digest?: string };
  reset: () => void;
}) {
  const router = useRouter();

  useEffect(() => {
    if (error.message === 'SETUP_REQUIRED') {
      const timer = setTimeout(() => {
        alert('설정이 필요합니다.');
        router.push('/settings');
      }, 0);
      return () => clearTimeout(timer);
    }

    if (error instanceof AxiosError && error.status === 401) {
      router.push('/login');
    }
  }, [error, router]);

  return <ErrorFallback error={error} resetErrorBoundary={reset} />;
}
