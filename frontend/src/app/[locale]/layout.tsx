import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from '@/providers/I18nProvider';
import { StoreProvider } from '@/providers/StoreProvider';
import ErrorBoundaryWrapper from '@/providers/ErrorBoundaryWrapper';

import styles from './layout.module.scss';
import { SnackbarProvider } from '@/components/Snackbar/SnackbarProvider';

const NavigationLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <ErrorBoundaryWrapper>
      <StoreProvider>
        <div className={styles['navigation-layout']}>
          <I18nProvider>
            <SnackbarProvider>
              <Navigation />
              {children}
            </SnackbarProvider>
          </I18nProvider>
        </div>
      </StoreProvider>
    </ErrorBoundaryWrapper>
  );
};

export default NavigationLayout;
