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

      {/* fontVariationSettings에 대한 설명: https://developers.google.com/fonts/docs/material_symbols?hl=ko#fill_axis */}
      <div style={{ fontVariationSettings: "'FILL' 1, 'wght' 700, 'GRAD' 0, 'opsz' 48;" }}>
        <Icon>search</Icon>
        <Icon>home</Icon>
        <Icon>settings</Icon>
        <Icon>favorite</Icon>
      </div>

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
