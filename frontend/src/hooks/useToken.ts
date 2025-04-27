'use client';

import { useEffect, useState } from 'react';

export function useToken() {
  const [token, setToken] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadedToken = localStorage.getItem('hada-token');
    if (loadedToken) {
      console.log('loaded token:', loadedToken);
      setToken(loadedToken);
      setLoading(false);
    } else {
      async function fetchToken() {
        try {
          const res = await fetch('/api/auth/get-token');
          const data = await res.json();

          if (!res.ok) {
            throw new Error(data.error || 'Failed to fetch token');
          }
          console.log('fetched token:', data);
          localStorage.setItem('hada-token', data.token);
          setToken(data.token);
        } catch (err: unknown) {
          if (err instanceof Error) {
            setError(err.message);
          } else {
            setError('An unknown error occurred');
          }
        } finally {
          setLoading(false);
        }
      }

      fetchToken();
    }
  }, []);

  return { token, loading, error };
}
