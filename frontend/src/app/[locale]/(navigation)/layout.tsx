import Navigation from '@/components/Navigation/Navigation';

import styles from './layout.module.scss';

const NavigationLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className={styles['navigation-layout']}>
      <Navigation />
      {children}
    </div>
  );
};

export default NavigationLayout;
