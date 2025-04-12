'use client';

import { useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import { redirect } from 'next/navigation';

export default function GoogleLoginRedirectPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const token = searchParams?.get('token'); // URL에서 token 읽기

  useEffect(() => {
    if (token) {
      localStorage.setItem('hada-token', token);
      redirect('/difficulty');
    } else {
      redirect('/login');
    }
  }, [token, router]);

  return (
    <div>
      <div>
        {token ? (
          <>
            <h1>로그인 성공</h1>
            <p>Auth Token:</p>
            <code>{token}</code>
          </>
        ) : (
          <>
            <h1>토큰이 없습니다</h1>
            <p>로그인 절차를 다시 시도해주세요.</p>
          </>
        )}
      </div>
    </div>
  );
}
