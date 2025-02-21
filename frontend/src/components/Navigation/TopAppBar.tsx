import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';

import styles from './TopAppBar.module.scss';

const TopAppBar = ({
  headline,
  leftIcon,
  rightIcon,
  onClickLeftIcon,
  onClickRightIcon
}: {
  headline: string;
  leftIcon?: string | React.ReactNode;
  rightIcon?: string | React.ReactNode;
  onClickLeftIcon?: () => void;
  onClickRightIcon?: () => void;
}) => {
  return (
    <div className={styles.container}>
      <div className={styles['icon-container']}>
        {leftIcon &&
          (typeof leftIcon === 'string' ? (
            <IconButton onClick={onClickLeftIcon}>
              <Icon>{leftIcon}</Icon>
            </IconButton>
          ) : (
            leftIcon
          ))}
      </div>
      <h1 className={styles['headline']}>{headline}</h1>
      <div className={styles['icon-container']}>
        {rightIcon &&
          (typeof rightIcon === 'string' ? (
            <IconButton onClick={onClickRightIcon}>
              <Icon>{rightIcon}</Icon>
            </IconButton>
          ) : (
            rightIcon
          ))}
      </div>
    </div>
  );
};

export default TopAppBar;
