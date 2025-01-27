import { useState } from 'react';

import { Ripple } from '@/components/material-components/Ripple';
import { Icon } from '@/components/material-components/IconButton/IconButton';

import styles from './NavigationBar.module.scss';

const NavigationBar = ({
  destinations,
  initialDestination
}: {
  destinations: { icon: string; label: string }[];
  initialDestination: string;
}) => {
  const [selectedDestination, setSelectedDestination] = useState<string>(initialDestination);

  const handleDestinationClick = (destination: string) => {
    setSelectedDestination(destination);
  };

  return (
    <nav className={styles.container}>
      {destinations.map((destination) => {
        const isSelected = selectedDestination === destination.label;
        return (
          <button
            className={`${styles.destination} ${isSelected ? styles.selected : ''}`}
            onClick={() => handleDestinationClick(destination.label)}
          >
            <div className={`${styles['icon-container']} ${isSelected ? styles.selected : ''}`}>
              <Ripple />
              <Icon>{destination.icon}</Icon>
            </div>
            <span
              className={`${styles['label']} ${isSelected ? styles.selected : ''} md-typescale-label-medium${isSelected ? '-prominent' : ''}`}
            >
              {destination.label}
            </span>
          </button>
        );
      })}
    </nav>
  );
};

export default NavigationBar;
