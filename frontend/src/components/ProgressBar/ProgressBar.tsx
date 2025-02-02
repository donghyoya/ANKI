import React from 'react';

import TooltipProvider from '@/components/Tooltips/TooltipProvider';

import styles from './ProgressBar.module.scss';

interface ProgressBarProps {
  bars: { value: number; label: string; tooltip?: string; color: string }[];
  styles?: React.CSSProperties;
  className?: string;
  height: number;
}

const ProgressBar = ({ bars, styles: stylesProp, className, height }: ProgressBarProps) => {
  const values = bars.map((bar) => bar.value);
  const sum = values.reduce((acc, num) => acc + num, 0);
  const percentages = values
    .map((_, index) => {
      const remainingSum = values.slice(index + 1).reduce((acc, num) => acc + num, 0);
      return (((sum - remainingSum) / sum) * 100).toFixed(1);
    })
    .reverse();

  return (
    <div className={`${styles['container']} ${className}`} style={{ ...stylesProp, height }}>
      {[...bars].reverse().map((bar, index) => {
        console.log(percentages[index]);
        return (
          <div
            key={index}
            className={styles['bar']}
            style={{
              backgroundColor: bar.color,
              width: percentages[index] + '%',
              paddingLeft: `${percentages[index + 1] ?? 0}%`,
              borderRadius: index === 0 ? '0' : `0 ${height / 32}rem ${height / 32}rem 0`
            }}
          >
            <div className={styles['label-container']}>
              {bar.tooltip && (
                <TooltipProvider text={bar.tooltip}>
                  <span className={`md-typescale-label-medium ${styles['label']}`}>
                    {+percentages[index] > 10 ? bar.label : ''}
                  </span>
                </TooltipProvider>
              )}
              {!bar.tooltip && (
                <span className={`md-typescale-label-medium ${styles['label']}`}>
                  {+percentages[index] > 10 ? bar.label : ''}
                </span>
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ProgressBar;
