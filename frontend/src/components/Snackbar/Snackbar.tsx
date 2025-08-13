'use client';

import styles from './Snackbar.module.scss';
import { Icon } from '../material-components/IconButton/IconButton';
import { motion } from 'motion/react';

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
    <motion.div
      className={styles.snackbar}
      initial={{ opacity: 0, y: 50 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: 50 }}
      transition={{ duration: 0.2, ease: 'easeInOut' }}
    >
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
    </motion.div>
  );
};

export default Snackbar;
