import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { refreshToken } from '@/api/utils';

import { RootState } from '@/store';

export const useValidatedToken = () => {
  const { accessToken } = useSelector((state: RootState) => state.auth);
  const [isRefreshing, setIsRefreshing] = useState(true);

  useEffect(() => {
    if (accessToken) {
      setIsRefreshing(false);
      return;
    }

    const initializeToken = async () => {
      try {
        await refreshToken();
      } catch (error) {
        console.error('토큰 갱신 실패', error);
      }
      setIsRefreshing(false);
    };
    initializeToken();
  }, [accessToken]);

  return { isRefreshing, accessToken };
};
