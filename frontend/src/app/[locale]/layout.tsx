import Navigation from '@/components/Navigation/Navigation';
import { SnackbarProvider } from '@/components/Snackbar/SnackbarProvider';

import I18nProvider from '@/providers/I18nProvider';
import { StoreProvider } from '@/providers/StoreProvider';
import { QueryProvider } from '@/providers/QueryProvider';
import ErrorBoundaryWrapper from '@/providers/ErrorBoundaryWrapper';

import { AuthService } from '@/services/AuthService';
import { CookieService } from '@/services/CookieService';

import styles from './layout.module.scss';

const NavigationLayout = async ({ children }: { children: React.ReactNode }) => {
  const cookieService = new CookieService();
  const authService = new AuthService(cookieService);
  const isLoggedIn = await authService.isLoggedIn();

  return (
    <ErrorBoundaryWrapper>
      <StoreProvider>
        <QueryProvider>
          <div className={styles['navigation-layout']}>
            <I18nProvider>
              <SnackbarProvider>
                <Navigation isLoggedIn={isLoggedIn} />
                {children}
              </SnackbarProvider>
            </I18nProvider>
          </div>
        </QueryProvider>
      </StoreProvider>
    </ErrorBoundaryWrapper>
  );
};

export default NavigationLayout;
