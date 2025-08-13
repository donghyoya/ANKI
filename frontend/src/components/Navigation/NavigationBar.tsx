import { useState } from 'react';
import { useTranslations } from 'next-intl';
import { motion } from 'framer-motion';

import { Icon } from '@/components/material-components/IconButton/IconButton';

import styles from './NavigationBar.module.scss';
import classNames from 'classnames';
import { useRouter } from 'next/navigation';

const NavigationBar = ({
  destinations,
  initialDestination,
  handleDestinationClick
}: {
  destinations: { icon: string; label: string }[];
  initialDestination: string;
  handleDestinationClick?: (label: string) => void;
}) => {
  const t = useTranslations();
  const router = useRouter();

  const [selectedDestination, setSelectedDestination] = useState<string>(initialDestination);

  const handleClick = (destination: string) => {
    setSelectedDestination(destination);
    if (handleDestinationClick) {
      handleDestinationClick(destination);
    } else {
      router.push(`/${destination}`);
    }
  };

  return (
    <nav className={styles.container}>
      {destinations.map((destination) => {
        const isSelected = selectedDestination === destination.label;
        return (
          <button
            key={destination.label}
            className={classNames(styles.destination, { [styles.selected]: isSelected })}
            onClick={() => handleClick(destination.label)}
          >
            <motion.div
              className={styles['icon-container']}
              animate={{
                fontVariationSettings:
                  selectedDestination === destination.label ? "'FILL' 1" : "'FILL' 0",
                transition: {
                  duration: 0.2,
                  ease: 'easeInOut'
                }
              }}
            >
              <motion.div
                className={styles['navigation-button-background']}
                initial={{ width: 0 }}
                animate={{
                  width: selectedDestination === destination.label ? '100%' : 0,
                  transition: {
                    duration: 0.2,
                    ease: 'easeInOut'
                  }
                }}
              />
              <Icon className={styles['icon']}>{destination.icon}</Icon>
            </motion.div>
            <span className={`${styles['label']} ${isSelected ? styles.selected : ''}`}>
              {t(`menu.${destination.label}`)}
            </span>
          </button>
        );
      })}
    </nav>
  );
};

export default NavigationBar;
