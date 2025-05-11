'use client';

import { useState } from 'react';
import { useTranslations } from 'next-intl';
import { useRouter } from 'next/navigation';

import { Ripple } from '@/components/material-components/Ripple';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';

import styles from './NavigationDrawer.module.scss';
import classNames from 'classnames';

const NavigationDrawer = ({
  destinations,
  initialDestination,
  toggleDrawer
}: {
  destinations: { icon: string; label: string }[];
  initialDestination: string;
  toggleDrawer: () => void;
}) => {
  const t = useTranslations();

  const [selectedDestination, setSelectedDestination] = useState<string>(initialDestination);
  const router = useRouter();

  const handleNavItemClick = (navItem: string) => {
    setSelectedDestination(navItem);
    router.push(`/${navItem}`);
  };

  return (
    <nav className={styles['container']}>
      <div className={styles['header-container']}>
        <div className={styles['header-button-container']}>
          <IconButton onClick={toggleDrawer}>
            <Icon>menu</Icon>
          </IconButton>
        </div>
        {/* TODO: <Logo /> */}
      </div>
      <div className={styles['list-container']}>
        {destinations.map((destination) => {
          const isSelected = selectedDestination === destination.label;
          return (
            <button
              key={destination.label}
              className={classNames(styles['item'], { [styles.selected]: isSelected })}
              onClick={() => handleNavItemClick(destination.label)}
            >
              <Ripple />
              <Icon>{destination.icon}</Icon>
              <span className={classNames(styles['item-label'], { [styles.selected]: isSelected })}>
                {t(`menu.${destination.label}`)}
              </span>
            </button>
          );
        })}
      </div>
    </nav>
  );
};

export default NavigationDrawer;
