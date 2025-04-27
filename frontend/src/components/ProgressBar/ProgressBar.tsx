import React from 'react';

import TooltipProvider from '@/components/Tooltips/TooltipProvider';
import styles from './ProgressBar.module.scss';
import { Progress } from '@/types/Progress';
import { LEARNING_PROGRESS_BAR_COLORS } from '@/constants/colors';

interface ProgressBarProps {
  progress: Progress[];
  styles?: React.CSSProperties;
  className?: string;
  height: number;
}

const pxToRem = (px: number) => `${px / 16}rem`;

const ProgressBar = ({ progress, styles: stylesProp, className, height }: ProgressBarProps) => {
  // values의 누적합의 비중을 계산하여 백분율로 변환
  // 오른쪽 끝 bar는 100%이고 오른쪽부터 왼쪽으로 겹쳐가면서 렌더링하기 위해 reverse
  const values = progress.map((bar) => bar.value);
  const sum = values.reduce((acc, num) => acc + num, 0);
  const percentages = values
    .map((_, index) => {
      const remainingSum = values.slice(index + 1).reduce((acc, num) => acc + num, 0);
      return +(((sum - remainingSum) / sum) * 100).toFixed(1);
    })
    .reverse();

  const fallbackProgress = [{ value: 0, label: '', color: LEARNING_PROGRESS_BAR_COLORS.new }];

  if (progress.some((p) => p.value === null)) {
    progress = fallbackProgress;
  }

  return (
    <div className={`${styles['container']} ${className}`} style={{ ...stylesProp, height }}>
      {[...progress].reverse().map((bar, index) => {
        return (
          <div
            key={index}
            className={styles['bar']}
            style={{
              backgroundColor: bar.color,
              width: percentages[index] + '%',
              paddingLeft: `${percentages[index + 1] ?? 0}%`,
              borderRadius: index === 0 ? '0' : `0 ${pxToRem(height / 2)} ${pxToRem(height / 2)} 0`
            }}
          >
            <div className={styles['label-container']}>
              <TooltipProvider text={bar.tooltip}>
                <span className={styles['label']}>{bar.label ? bar.label : ''}</span>
              </TooltipProvider>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ProgressBar;
