import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from '@/providers/I18nProvider';
import { StoreProvider } from '@/providers/StoreProvider';
import ErrorBoundaryWrapper from '@/providers/ErrorBoundaryWrapper';

import styles from './layout.module.scss';
import { AuthService } from '@/services/AuthService';
import { CookieService } from '@/services/CookieService';

const NavigationLayout = async ({ children }: { children: React.ReactNode }) => {
  const cookieService = new CookieService();
  const authService = new AuthService(cookieService);
  const isLoggedIn = await authService.isLoggedIn();

  return (
    <ErrorBoundaryWrapper>
      <StoreProvider>
        <div className={styles['navigation-layout']}>
          <I18nProvider>
            <Navigation isLoggedIn={isLoggedIn} />
            {children}
          </I18nProvider>
        </div>
      </StoreProvider>
    </ErrorBoundaryWrapper>
  );
};

export default NavigationLayout;
