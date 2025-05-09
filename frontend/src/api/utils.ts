import { getToken } from './auth';
import { setAccessToken } from '@/store/slices/authSlice';
import { getCookie, setCookie } from './cookie';
import { store } from '@/store';

export class ApiError extends Error {
  constructor(
    public status: number,
    public message: string,
    public data?: unknown
  ) {
    super(message);
    this.name = 'ApiError';
  }
}

interface ApiRequestParams<T = unknown> {
  url: string;
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE';
  token?: string;
  body?: T;
}

async function handleTokenRefresh<T = unknown>(params: ApiRequestParams<T>) {
  try {
    const { url, method = 'GET', body } = params;

    const refreshToken = await getCookie('refreshToken');
    if (!refreshToken.value) {
      throw new ApiError(401, 'UNAUTHORIZED');
    }

    const newTokens = await getToken(refreshToken.value);
    store.dispatch(setAccessToken(newTokens.accessToken));
    setCookie({ name: 'refreshToken', value: newTokens.refreshToken });

    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${newTokens.accessToken}`
    };

    const response = await fetch(url, {
      method,
      headers,
      body: body ? JSON.stringify(body) : undefined
    });

    return response;
  } catch (error) {
    if (error instanceof ApiError) {
      throw error;
    }
    throw new ApiError(500, '토큰 갱신 중 오류 발생', error);
  }
}

export async function apiRequest<T, B = unknown>(params: ApiRequestParams<B>): Promise<T> {
  try {
    const { url, method = 'GET', token, body } = params;

    const headers = {
      'Content-Type': 'application/json'
    } as Record<string, string>;

    if (token) {
      headers.Authorization = `Bearer ${token}`;
    }

    const response = await fetch(url, {
      method,
      headers,
      body: body ? JSON.stringify(body) : undefined
    });
    const data = await response.json();

    if (response.ok) {
      return data;
    }

    // 에러 핸들링
    if (response.status === 401) {
      const retryResponse = await handleTokenRefresh(params);
      if (retryResponse.ok) {
        return retryResponse.json();
      }
    }

    throw new ApiError(response.status, data.message || 'API 요청 실패', data);
  } catch (error) {
    if (error instanceof ApiError) {
      throw error;
    }
    throw new ApiError(500, '알 수 없는 오류 발생', error);
  }
}
