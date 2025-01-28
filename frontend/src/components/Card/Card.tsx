import React from 'react';

import { Elevation } from '@/components/material-components/Elevation/Elevation';
import { Ripple } from '@/components/material-components/Ripple';

import styles from './Card.module.scss';

export const ElevatedCard = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className={styles['elevated-card']}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const FilledCard = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className={styles['filled-card']}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const OutlinedCard = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className={styles['outlined-card']}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const CardExample = () => {
  return (
    <div className={styles['card-example']}>
      <ElevatedCard>
        <div className={styles['inner-container']}>Elevated Card</div>
      </ElevatedCard>
      <FilledCard>
        <div className={styles['inner-container']}>Filled Card</div>
      </FilledCard>
      <OutlinedCard>
        <div className={styles['inner-container']}>Outlined Card</div>
      </OutlinedCard>
    </div>
  );
};
