'use client';

import { useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import { redirect } from 'next/navigation';
import { getUserOption } from '@/api/option';
import { getToken } from '@/api/auth';

export default function GoogleLoginRedirectPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const authenticationToken = searchParams?.get('token'); // URL에서 token 읽기

  useEffect(() => {
    (async function () {
      if (authenticationToken) {
        const { accessToken, refreshToken } = await getToken(authenticationToken);
        console.log(accessToken);
        localStorage.setItem('hada-access-token', accessToken);
        localStorage.setItem('hada-refresh-token', refreshToken);
        const userOption = await getUserOption(accessToken);

        if (userOption.utcOffset === null) {
          redirect('/settings');
        } else {
          redirect('/difficulty');
        }
      } else {
        redirect('/login');
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
