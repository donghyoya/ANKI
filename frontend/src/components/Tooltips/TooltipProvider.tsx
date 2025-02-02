import { createPortal } from 'react-dom';
import { useEffect, useRef, useState } from 'react';

import styles from './TooltipProvider.module.scss';
import Tooltip from './Tooltip';

const TooltipProvider = ({
  children,
  text,
  extraGap
}: {
  children: React.ReactNode;
  text: string;
  extraGap?: boolean;
}) => {
  const wrapperRef = useRef<HTMLDivElement>(null);
  const [rect, setRect] = useState<DOMRect>();

  useEffect(() => {
    if (!wrapperRef.current) return;
    setRect(wrapperRef.current.getBoundingClientRect());
    console.log(rect);
  }, [text]);

  return (
    <div className={styles['tooltip-wrap']} ref={wrapperRef}>
      {children}
      {rect && createPortal(<Tooltip text={text} rect={rect} />, document.body)}
    </div>
  );
};

export default TooltipProvider;
