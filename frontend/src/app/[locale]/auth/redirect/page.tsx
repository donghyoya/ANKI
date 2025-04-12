'use client';

import { useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';

export default function GoogleLoginRedirectPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const token = searchParams?.get('token'); // URL에서 token 읽기

  useEffect(() => {
    if (token) {
      fetch('/api/auth/set-token', {
        // next.js 서버에 연락. token을 next.js 서버의 cookie로 설정
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ token: token })
      }).then(() => {
        // router.replace('/auth/test');
        localStorage.setItem('hada-token', token);
      });
    } else {
      // router.replace('/login');
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
