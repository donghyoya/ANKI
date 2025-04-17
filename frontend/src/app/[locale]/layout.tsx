import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from '../../providers/I18nProvider';

import styles from './layout.module.scss';

const NavigationLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className={styles['navigation-layout']}>
      <I18nProvider>
        <Navigation />
        {children}
      </I18nProvider>
    </div>
  );
};

export default NavigationLayout;
