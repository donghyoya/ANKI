import React from 'react';

import { Elevation } from '@/components/material-components/Elevation/Elevation';
import { Ripple } from '@/components/material-components/Ripple';

import styles from './Card.module.scss';
import classNames from 'classnames';

interface CardProps {
  children: React.ReactNode;
  className?: string;
  style?: React.CSSProperties;
  ripple?: boolean;
  onClick?: () => void;
}

export const ElevatedCard = ({
  children,
  style: propsStyle,
  className: propsClassName,
  ripple: propsRipple = true,
  onClick
}: CardProps) => {
  return (
    <div
      className={classNames(styles['elevated-card'], propsClassName)}
      style={propsStyle}
      onClick={onClick}
    >
      <Elevation />
      {propsRipple && <Ripple />}
      {children}
    </div>
  );
};

export const FilledCard = ({
  children,
  style: propsStyle,
  className: propsClassName,
  ripple: propsRipple = true,
  onClick
}: CardProps) => {
  return (
    <div
      className={classNames(styles['filled-card'], propsClassName)}
      style={propsStyle}
      onClick={onClick}
    >
      <Elevation />
      {propsRipple && <Ripple />}
      {children}
    </div>
  );
};

export const OutlinedCard = ({
  children,
  style: propsStyle,
  className: propsClassName,
  ripple: propsRipple = true,
  onClick
}: CardProps) => {
  return (
    <div
      className={classNames(styles['outlined-card'], propsClassName)}
      style={propsStyle}
      onClick={onClick}
    >
      <Elevation />
      {propsRipple && <Ripple />}
      {children}
    </div>
  );
};

export const CardExample = () => {
  return (
    <div className={styles['card-example-container']}>
      <ElevatedCard className={styles['card-example']} ripple={false}>
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
