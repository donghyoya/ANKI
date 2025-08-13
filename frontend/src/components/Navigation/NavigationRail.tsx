'use client';

import { useState } from 'react';
import { useTranslations } from 'next-intl';
import classnames from 'classnames';
import { motion } from 'motion/react';

import styles from './NavigationRail.module.scss';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import { useWindowSize } from '@/hooks/useWindowSize';

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

  const [selectedDestination, setSelectedDestination] = useState<string | null>(initialDestination);
  const [isExpanded, setIsExpanded] = useState(false);
  const { width } = useWindowSize();

  const handleDestinationClick = (destination: string) => {
    setSelectedDestination(destination);
  };

  const handleExpand = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <>
      <motion.nav
        className={classnames(styles.container, isExpanded && styles.expanded)}
        initial={{ width: 80 }}
        animate={{ width: isExpanded ? 250 : 80 }}
      >
        <motion.div
          className={styles['header-container']}
          initial={{ height: 64 }}
          animate={{ height: isExpanded ? 52 : 64 }}
          transition={{
            duration: 0.2,
            ease: 'easeInOut'
          }}
        >
          {isMenuEnabled && (
            <IconButton onClick={handleExpand}>
              <Icon>{isExpanded ? 'menu_open' : 'menu'}</Icon>
            </IconButton>
          )}
        </motion.div>
        <div className={styles['navigation-container']}>
          {destinations.map((destination) => (
            <div
              key={destination.label}
              className={classnames(
                styles['navigation-item'],
                selectedDestination === destination.label && styles['selected']
              )}
              onClick={() => handleDestinationClick(destination.label)}
            >
              <button className={styles['navigation-item-button']}>
                <motion.div
                  className={styles['navigation-button-background-container']}
                  animate={{
                    height: isExpanded ? '56px' : '32px',
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
                </motion.div>
                <motion.div
                  className={styles['navigation-item-icon']}
                  animate={{
                    fontVariationSettings:
                      selectedDestination === destination.label ? "'FILL' 1" : "'FILL' 0",
                    top: isExpanded ? 16 : 4,
                    transition: {
                      duration: 0.2,
                      ease: 'easeInOut'
                    }
                  }}
                >
                  <Icon>{destination.icon}</Icon>
                </motion.div>
                {isExpanded && (
                  <motion.span
                    className={styles['navigation-item-label']}
                    initial={{ opacity: 0 }}
                    animate={{ opacity: 1 }}
                    transition={{
                      duration: 0.2,
                      ease: 'easeInOut'
                    }}
                  >
                    {t(`menu.${destination.label}`)}
                  </motion.span>
                )}
              </button>
              {!isExpanded && (
                <motion.span
                  className={styles['navigation-item-label']}
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  transition={{
                    duration: 0.2,
                    ease: 'easeInOut'
                  }}
                >
                  {t(`menu.${destination.label}`)}
                </motion.span>
              )}
            </div>
          ))}
        </div>
      </motion.nav>
      {isExpanded && width < 1200 && (
        <motion.div
          className={styles.scrim}
          onClick={handleExpand}
          initial={{ opacity: 0 }}
          animate={{ opacity: 0.2 }}
          exit={{ opacity: 0 }}
          transition={{ duration: 0.2, ease: 'easeInOut' }}
        />
      )}
    </>
  );
};

export default NavigationRail;
