import { jwtDecode } from 'jwt-decode';

import { CookieService } from './CookieService';
import { ServerServiceFactory } from './ServerServiceFactory';

import { refresh } from '@/api/auth';
import { TIME } from '@/constants/time';

export class AuthService {
  constructor(private cookieService: CookieService) {}

  async refresh(): Promise<void> {
    const localRefreshToken = await this.getRefreshToken();
    if (!localRefreshToken) throw new Error('No refresh token');
    await refresh(localRefreshToken);
  }

  async getAccessToken(): Promise<string | null> {
    try {
      const shouldRefresh = await this.shouldRefreshToken();
      if (shouldRefresh) {
        await this.refresh(); // 실패 시 여기서 예외 발생
      }
    } catch (e) {
      console.log('refresh failed:', e);
      return null;
    }

    return await this.cookieService.get('accessToken');
  }

  async saveToken(accessToken: string, refreshToken: string): Promise<void> {
    await this.cookieService.set('accessToken', accessToken);
    await this.cookieService.set('refreshToken', refreshToken);
  }

  private async shouldRefreshToken(): Promise<boolean> {
    const accessToken = await this.cookieService.get('accessToken');
    if (!accessToken) return true;
    const decodedToken = jwtDecode(accessToken);
    if (!decodedToken.exp) throw new Error('Invalid token');
    return decodedToken.exp * 1000 < Date.now() + TIME.MINUTE_IN_MS * 5;
  }

  private async getRefreshToken(): Promise<string | null> {
    return await this.cookieService.get('refreshToken');
  }

  async isLoggedIn(): Promise<boolean> {
    const accessToken = await this.getAccessToken();
    if (!accessToken) return false;
    try {
      const httpClient = ServerServiceFactory.getHttpClient();
      await httpClient.get('/auth-test'); // 서버에서 토큰 유효성 검사
      return true;
    } catch (e) {
      console.log('login error:', e);
      return false;
    }
  }
}
