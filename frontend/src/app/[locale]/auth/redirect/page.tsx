'use client';

import { useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import { redirect } from 'next/navigation';

import { CookieService } from '@/services/CookieService';
import { AuthService } from '@/services/AuthService';

export default function GoogleLoginRedirectPage() {
  // URL에서 임시 토큰 읽기
  const router = useRouter();
  const searchParams = useSearchParams();
  const authenticationToken = searchParams?.get('token');

  // 토큰 저장 로직
  useEffect(() => {
    (async function () {
      if (!authenticationToken) {
        redirect('/login');
        return;
      }

      const authService = new AuthService(new CookieService());
      const isSetup = await authService.handleAuthRedirect(authenticationToken);

      if (isSetup) {
        redirect('/difficulty');
      } else {
        redirect('/settings');
      }
    })();
  }, [authenticationToken, router]);

  return (
    <div>
      <div>
        {authenticationToken ? (
          <>
            <h1>로그인 성공</h1>
            <p>Auth Token:</p>
            <code>{authenticationToken}</code>
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
