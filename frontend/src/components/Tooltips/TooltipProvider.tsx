'use client';

import { useEffect, useRef, useState } from 'react';
import { createPortal } from 'react-dom';
import { debounce } from 'lodash';
import Tooltip from './Tooltip';

import styles from './TooltipProvider.module.scss';

const DEBOUNCE_TIME = 100;

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
    const handleResize = debounce(() => {
      if (!wrapperRef.current) return;
      setRect(wrapperRef.current.getBoundingClientRect());
    }, DEBOUNCE_TIME);

    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
      handleResize.cancel();
    };
  }, []);

  useEffect(() => {
    if (!wrapperRef.current) return;
    setRect(wrapperRef.current.getBoundingClientRect());
    console.log(rect);
  }, [text]);

  return (
    <div className={styles['tooltip-wrap']} ref={wrapperRef}>
      {children}
      {rect && createPortal(<Tooltip text={text} rect={rect} extraGap={extraGap} />, document.body)}
    </div>
  );
};

export default TooltipProvider;
