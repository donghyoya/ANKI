'use server';

import { TokenDTO } from '@/types/schemes';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const refresh = async (authenticationToken: string): Promise<TokenDTO> => {
  try {
    const url = `${endpoint}/auth/token`;
    console.log('authenticationToken', authenticationToken);

    const response = await fetch(url, {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${authenticationToken}`,
        'Content-Type': 'application/json'
      }
    });

    const data = await response.json();

    if (response.ok) {
      const cookieService = ServerServiceFactory.getCookieService();
      await cookieService.set('accessToken', data.accessToken);
      await cookieService.set('refreshToken', data.refreshToken);
      return data as TokenDTO;
    }

    throw new Error('토큰 발급 실패');
  } catch (error) {
    throw error;
  }
};
