'use client';

import { ErrorBoundary } from 'react-error-boundary';
import { ErrorFallback } from '@/components/ErrorFallback';

export default function ErrorBoundaryWrapper({ children }: { children: React.ReactNode }) {
  const handleReset = () => {
    window.location.reload();
  };

  return (
    <ErrorBoundary FallbackComponent={ErrorFallback} onReset={handleReset}>
      {children}
    </ErrorBoundary>
  );
}
