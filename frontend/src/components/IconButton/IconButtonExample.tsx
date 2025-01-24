'use client';

import {
  Icon,
  IconButton,
  FilledIconButton,
  FilledTonalIconButton,
  OutlinedIconButton
} from './IconButton';
import styles from './IconButtonExample.module.scss';

export const IconButtonExample = () => {
  return (
    <>
      <Icon className={styles['icon']}>check</Icon>
      <IconButton className={styles['icon-button']}>
        <Icon>check</Icon>
      </IconButton>
      <FilledIconButton className={styles['filled-icon-button']}>
        <Icon>check</Icon>
      </FilledIconButton>
      <FilledTonalIconButton className={styles['filled-tonal-icon-button']}>
        <Icon>check</Icon>
      </FilledTonalIconButton>
      <OutlinedIconButton className={styles['outlined-icon-button']}>
        <Icon>check</Icon>
      </OutlinedIconButton>
    </>
  );
};
