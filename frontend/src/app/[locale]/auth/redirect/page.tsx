'use client';

import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useRouter, useSearchParams } from 'next/navigation';
import { redirect } from 'next/navigation';

import { getToken } from '@/api/auth';
import { setCookie } from '@/api/cookie';
import { setAccessToken } from '@/store/slices/authSlice';

export default function GoogleLoginRedirectPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const dispatch = useDispatch();

  // URL에서 임시 토큰 읽기
  const authenticationToken = searchParams?.get('token');

  // 토큰 저장 로직
  useEffect(() => {
    (async function () {
      console.log(authenticationToken);
      if (authenticationToken) {
        const { accessToken, refreshToken, isSetup } = await getToken(authenticationToken);
        console.log('getToken:', { accessToken, refreshToken, isSetup });

        // accessToken을 store에 저장
        dispatch(setAccessToken(accessToken));
        // refreshToken을 cookie에 저장
        setCookie({ name: 'refreshToken', value: refreshToken });

        // 유저의 옵션 상태에 따라 리디렉션
        if (isSetup) {
          redirect('/difficulty');
        } else {
          redirect('/settings');
        }
      } else {
        redirect('/login');
      }
    })();
  }, [authenticationToken, router, dispatch]);

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
