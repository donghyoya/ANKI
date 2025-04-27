'use client';

import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

import styles from './error.module.scss';

export default function Error({ error }: { error: Error }) {
  const router = useRouter();

  useEffect(() => {
    if (error.message === 'SETUP_REQUIRED') {
      const timer = setTimeout(() => {
        alert('설정이 필요합니다.');
        router.push('/settings');
      }, 0);
      return () => clearTimeout(timer);
    }
  }, [error, router]);

  console.log('Error details:', {
    message: error.message,
    name: error.name,
    stack: error.stack
  });

  return (
    <div className={styles['error-container']}>
      <div className={styles['error-content']}>
        <h1 className={styles['error-title']}>에러가 발생했습니다</h1>
        <p className={styles['error-message']}>{error.message}</p>
        <div className={styles['error-details']}>
          <p>에러 유형: {error.name}</p>
          {error.stack && <pre className={styles['error-stack']}>{error.stack}</pre>}
        </div>
      </div>
    </div>
  );
}
