import { AuthInterceptor } from './AuthInterceptor';
import { CookieService } from './CookieService';
import { HttpClient } from './HttpClient';
import { AuthService } from './AuthService';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export class ServerServiceFactory {
  private static cookieService: CookieService;
  private static authService: AuthService;
  private static httpClient: HttpClient;

  static getCookieService(): CookieService {
    if (!this.cookieService) {
      this.cookieService = new CookieService();
    }
    return this.cookieService;
  }

  static getAuthService(): AuthService {
    if (!this.authService) {
      this.authService = new AuthService(this.getCookieService());
    }
    return this.authService;
  }

  static getHttpClient(): HttpClient {
    if (!this.httpClient) {
      if (!endpoint) throw new Error('NEXT_PUBLIC_API_URL is not defined');
      const authInterceptor = new AuthInterceptor(this.getAuthService());
      this.httpClient = new HttpClient(endpoint, authInterceptor);
    }
    return this.httpClient;
  }
}
