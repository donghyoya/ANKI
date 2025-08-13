import { AxiosError, AxiosResponse, InternalAxiosRequestConfig } from 'axios';

import { AuthService } from './AuthService';

export class AuthInterceptor {
  constructor(private authService: AuthService) {}

  // 요청 인터셉터
  async onRequest(config: InternalAxiosRequestConfig): Promise<InternalAxiosRequestConfig> {
    // 요청 전 토큰 처리
    const accessToken = await this.authService.getAccessToken();
    if (!accessToken) return config;

    config.headers['Authorization'] = `Bearer ${accessToken}`;
    return config;
  }

  // 응답 인터셉터
  async onResponse(response: AxiosResponse): Promise<AxiosResponse> {
    // 응답 후 처리
    return response;
  }

  // 에러 인터셉터
  async onError(error: AxiosError): Promise<AxiosError> {
    // 에러 처리 및 토큰 갱신
    if (error.response?.status === 401) {
      await this.authService.refresh();
    }
    return error;
  }
}
