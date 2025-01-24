'use client';

import { useState } from 'react';
import styles from './NavigationRail.module.scss';
import { Icon, IconButton } from '@/components/IconButton/IconButton';

const NavigationRail = ({
  destinations,
  isMenuEnabled,
  initialDestination,
  toggleDrawer
}: {
  destinations: { icon: string; label: string }[];
  isMenuEnabled: boolean;
  initialDestination: string;
  toggleDrawer: () => void;
}) => {
  const [selectedDestination, setSelectedDestination] = useState<string | null>(initialDestination);

  const handleDestinationClick = (destination: string) => {
    setSelectedDestination(destination);
  };

  return (
    <nav className={styles.container}>
      <div className={styles['header-container']}>
        {isMenuEnabled && (
          <IconButton onClick={toggleDrawer}>
            <Icon>menu</Icon>
          </IconButton>
        )}
      </div>
      <div className={styles['navigation-container']}>
        {destinations.map((destination) => (
          <div
            key={destination.label}
            className={styles['navigation-item-container']}
            onClick={() => handleDestinationClick(destination.label)}
          >
            <button
              className={`${styles['navigation-item-button']} ${selectedDestination === destination.label ? styles['selected'] : ''}`}
            >
              <div className={styles['state-layer']} />
              <div className={styles['navigation-item-icon']}>
                <Icon>{destination.icon}</Icon>
              </div>
            </button>
            <div
              className={`${styles['navigation-item-label']} ${selectedDestination === destination.label ? styles['selected'] : ''}`}
            >
              <span
                className={`md-typescale-label-medium${selectedDestination === destination.label ? styles['-prominent'] : ''}`}
              >
                {destination.label}
              </span>
            </div>
          </div>
        ))}
      </div>
    </nav>
  );
};

export default NavigationRail;
