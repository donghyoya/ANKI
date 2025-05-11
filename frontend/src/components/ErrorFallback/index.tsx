'use client';

import { FallbackProps } from 'react-error-boundary';

import styles from './ErrorFallback.module.scss';

export function ErrorFallback({ error, resetErrorBoundary }: FallbackProps) {
  console.log('error:', error);
  return (
    <div className={styles.page}>
      <h1>에러가 발생했습니다</h1>
      <p>{error.message}</p>
      <p>error fallback</p>
      <button onClick={resetErrorBoundary}>다시 시도</button>
    </div>
  );
}
