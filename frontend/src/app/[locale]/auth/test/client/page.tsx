'use client';

import { useEffect, useState } from 'react';
import { useToken } from '@/hooks/useToken';

export default function AuthTestClientPage() {
  const { token, loading: isTokenLoading } = useToken();
  const [username, setUsername] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function fetchAuthTest() {
      if (!token || isTokenLoading) return;
      console.log('token:', token);
      try {
        const res = await fetch(`${process.env.NEXT_PUBLIC_SERVER}/auth-test`, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        });

        if (res.status === 401) {
          setError('로그인이 필요합니다.');
          setUsername(null);
          return;
        }

        if (!res.ok) {
          throw new Error('Failed to fetch');
        }

        const data = await res.json();
        setUsername(data.username);
        setError(null);
      } catch (err) {
        console.error(err);
        setError('서버 오류가 발생했습니다.');
        setUsername(null);
      }
    }

    fetchAuthTest();
  }, [token, isTokenLoading]);

  if (isTokenLoading) {
    return <h1 className="text-2xl font-semibold text-gray-700">토큰 로딩중...</h1>;
  }

  return (
    <h1>{error ? error : username ? `Welcome, ${username}!` : '사용자 정보를 불러오는 중...'}</h1>
  );
}
