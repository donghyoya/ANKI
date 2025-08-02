import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from '@/providers/I18nProvider';
import { StoreProvider } from '@/providers/StoreProvider';
import ErrorBoundaryWrapper from '@/providers/ErrorBoundaryWrapper';
import { QueryProvider } from '@/providers/QueryProvider';

import styles from './layout.module.scss';
import { SnackbarProvider } from '@/components/Snackbar/SnackbarProvider';

const NavigationLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <ErrorBoundaryWrapper>
      <StoreProvider>
        <QueryProvider>
          <div className={styles['navigation-layout']}>
            <I18nProvider>
              <SnackbarProvider>
                <Navigation />
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
