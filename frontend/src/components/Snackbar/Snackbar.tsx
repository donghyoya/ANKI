'use client';

import styles from './Snackbar.module.scss';
import { Icon } from '../material-components/IconButton/IconButton';

interface SnackbarProps {
  text: string;
  actionLabel?: string;
  onAction?: () => void;
  closable?: boolean;
  onRequestClose: () => void;
}

const Snackbar = ({
  text,
  actionLabel,
  onAction,
  closable = false,
  onRequestClose
}: SnackbarProps) => {
  return (
    <div className={styles.snackbar}>
      <span className={styles.text}>{text}</span>
      {actionLabel && (
        <button className={styles.action} onClick={onAction}>
          {actionLabel}
        </button>
      )}
      {closable && (
        <button className={styles.close} onClick={onRequestClose}>
          <Icon>close</Icon>
        </button>
      )}
    </div>
  );
};

export default Snackbar;
