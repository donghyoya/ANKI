import React from 'react';

import { Elevation } from '@/components/material-components/Elevation/Elevation';
import { Ripple } from '@/components/material-components/Ripple';

import styles from './Card.module.scss';

interface CardProps {
  children: React.ReactNode;
  className?: string;
  style?: React.CSSProperties;
}

export const ElevatedCard = ({
  children,
  style: propsStyle,
  className: propsClassName
}: CardProps) => {
  return (
    <div className={`${styles['elevated-card']} ${propsClassName || ''}`} style={propsStyle}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const FilledCard = ({
  children,
  style: propsStyle,
  className: propsClassName
}: CardProps) => {
  return (
    <div className={`${styles['filled-card']} ${propsClassName || ''}`} style={propsStyle}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const OutlinedCard = ({
  children,
  style: propsStyle,
  className: propsClassName
}: CardProps) => {
  return (
    <div className={`${styles['outlined-card']} ${propsClassName || ''}`} style={propsStyle}>
      <Elevation />
      <Ripple />
      {children}
    </div>
  );
};

export const CardExample = () => {
  return (
    <div className={styles['card-example-container']}>
      <ElevatedCard className={styles['card-example']}>
        <div className={styles['inner-container']}>Elevated Card</div>
      </ElevatedCard>
      <FilledCard className={styles['card-example']}>
        <div className={styles['inner-container']}>Filled Card</div>
      </FilledCard>
      <OutlinedCard className={styles['card-example']}>
        <div className={styles['inner-container']}>Outlined Card</div>
      </OutlinedCard>
    </div>
  );
};
