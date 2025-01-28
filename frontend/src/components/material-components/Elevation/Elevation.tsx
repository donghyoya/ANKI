import React from 'react';

import { createComponent } from '@lit/react';
import { MdElevation } from '@material/web/elevation/elevation';
import '@material/web/elevation/internal/elevation';

import styles from './Elevation.module.scss';

export const Elevation = createComponent({
  tagName: 'md-elevation',
  elementClass: MdElevation,
  react: React
});

const ElevationExample = () => {
  return (
    <div className={styles['container']}>
      <Elevation />
      Hello
    </div>
  );
};

export default ElevationExample;
