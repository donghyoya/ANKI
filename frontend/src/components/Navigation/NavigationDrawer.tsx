'use client';

import { useState } from 'react';

import { Ripple } from '@/components/material-components/Ripple';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import styles from './NavigationDrawer.module.scss';

const NavigationDrawer = ({
  destinations,
  initialDestination,
  toggleDrawer
}: {
  destinations: { icon: string; label: string }[];
  initialDestination: string;
  toggleDrawer: () => void;
}) => {
  const [selectedDestination, setSelectedDestination] = useState<string>(initialDestination);

  const handleNavItemClick = (navItem: string) => {
    setSelectedDestination(navItem);
  };

  return (
    <nav className={styles['container']}>
      <div className={styles['header-container']}>
        <div className={styles['header-button-container']}>
          <IconButton onClick={toggleDrawer}>
            <Icon>menu</Icon>
          </IconButton>
        </div>
        {/* <Logo /> */}
      </div>
      <div className={styles['list-container']}>
        {destinations.map((destination) => {
          const isSelected = selectedDestination === destination.label;
          return (
            <button
              key={destination.label}
              className={`${styles['item']} ${isSelected ? styles.selected : ''}`}
              onClick={() => handleNavItemClick(destination.label)}
            >
              <Ripple />
              <Icon>{destination.icon}</Icon>
              <span
                className={`${styles['item-label']} md-typescale-label-large${isSelected ? '-prominent' : ''}`}
              >
                {destination.label}
              </span>
            </button>
          );
        })}
      </div>
    </nav>
  );
};

export default NavigationDrawer;
