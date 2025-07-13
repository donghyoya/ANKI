'use client';

import { useState } from 'react';
import { useTranslations } from 'next-intl';
import classnames from 'classnames';

import styles from './NavigationRail.module.scss';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import { Ripple } from '@/components/material-components/Ripple';
import { useRouter } from 'next/navigation';

const NavigationRail = ({
  destinations,
  isMenuEnabled,
  initialDestination
}: {
  destinations: { icon: string; label: string }[];
  isMenuEnabled: boolean;
  initialDestination: string;
}) => {
  const t = useTranslations();
  const router = useRouter();

  const [selectedDestination, setSelectedDestination] = useState<string | null>(initialDestination);
  const [isExpanded, setIsExpanded] = useState(false);

  const handleDestinationClick = (destination: string) => {
    setSelectedDestination(destination);
  };

  const handleClick = (destination: string) => {
    setSelectedDestination(destination);
    if (handleDestinationClick) {
      handleDestinationClick(destination);
    } else {
      router.push(`/${destination}`);
    }
  };

  const handleExpand = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <nav className={classnames(styles.container, isExpanded && styles.expanded)}>
      <div className={styles['header-container']}>
        {isMenuEnabled && (
          <IconButton onClick={handleExpand}>
            <Icon>{isExpanded ? 'menu_open' : 'menu'}</Icon>
          </IconButton>
        )}
      </div>
      <div className={styles['navigation-container']}>
        {destinations.map((destination) => (
          <div
            key={destination.label}
            className={styles['navigation-item-container']}
            onClick={() => handleClick(destination.label)}
          >
            <button
              className={classnames(styles['navigation-item-button'], {
                [styles['selected']]: selectedDestination === destination.label
              })}
            >
              <Ripple />
              <div className={styles['navigation-item-icon']}>
                <Icon>{destination.icon}</Icon>
              </div>
              {isExpanded && (
                <span className={styles['navigation-item-label']}>
                  {t(`menu.${destination.label}`)}
                </span>
              )}
            </button>
            <div
              className={classnames(styles['navigation-item-label'], {
                [styles['selected']]: selectedDestination === destination.label
              })}
            >
              <span>{t(`menu.${destination.label}`)}</span>
            </div>
          </div>
        ))}
      </div>
    </nav>
  );
};

export default NavigationRail;
