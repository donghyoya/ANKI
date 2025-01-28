import styles from './TooltipProvider.module.scss';

const TooltipProvider = ({
  children,
  text,
  extraGap
}: {
  children: React.ReactNode;
  text: string;
  extraGap?: boolean;
}) => {
  return (
    <div className={styles['tooltip-wrap']}>
      <div
        className={`${styles['tooltip']} ${extraGap ? styles['extra-gap'] : ''}`}
        data-tooltip={text}
      >
        {children}
      </div>
    </div>
  );
};

export default TooltipProvider;
