'use client';

import { useEffect, useState } from 'react';

export function useToken() {
  const [token, setToken] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadedToken = localStorage.getItem('hada-access-token');
    if (loadedToken) {
      console.log('loaded token:', loadedToken);
      setToken(loadedToken);
    }
    setLoading(false);
  }, []);

  return { token, loading };
}
