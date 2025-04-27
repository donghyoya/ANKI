import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from '@/providers/I18nProvider';
import { StoreProvider } from '@/providers/StoreProvider';

import styles from './layout.module.scss';

const NavigationLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <StoreProvider>
      <div className={styles['navigation-layout']}>
        <I18nProvider>
          <Navigation />
          {children}
        </I18nProvider>
      </div>
    </StoreProvider>
  );
};

export default NavigationLayout;
