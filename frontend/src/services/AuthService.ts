import { jwtDecode } from 'jwt-decode';

import { CookieService } from './CookieService';

import { getToken } from '@/api/auth';
import { TIME } from '@/constants/time';

export class AuthService {
  constructor(private cookieService: CookieService) {}

  async refreshToken(): Promise<void> {
    const localRefreshToken = await this.getRefreshToken();
    if (!localRefreshToken) throw new Error('No refresh token');

    const { accessToken, refreshToken } = await getToken(localRefreshToken);
    await this.saveToken(accessToken, refreshToken);
  }

  async getAccessToken(): Promise<string | null> {
    if (await this.shouldRefreshToken()) {
      await this.refreshToken();
    }
    return await this.cookieService.get('accessToken');
  }

  async saveToken(accessToken: string, refreshToken: string): Promise<void> {
    await this.cookieService.set('accessToken', accessToken);
    await this.cookieService.set('refreshToken', refreshToken);
  }

  async handleAuthRedirect(token: string) {
    const { accessToken, refreshToken, isSetup } = await getToken(token);
    await this.saveToken(accessToken, refreshToken);
    return isSetup;
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
}
