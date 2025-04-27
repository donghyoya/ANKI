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
  text: string | undefined;
  extraGap?: boolean;
}) => {
  const wrapperRef = useRef<HTMLDivElement>(null);
  const [rect, setRect] = useState<DOMRect>();

  const updatePosition = debounce(() => {
    if (!wrapperRef.current) return;
    setRect(wrapperRef.current.getBoundingClientRect());
  }, DEBOUNCE_TIME);

  useEffect(() => {
    const observer = new ResizeObserver(updatePosition);
    const current = wrapperRef.current;
    if (current) {
      observer.observe(current);
    }

    window.addEventListener('scroll', updatePosition, true);
    window.addEventListener('resize', updatePosition);

    return () => {
      if (current) {
        observer.unobserve(current);
      }
      observer.disconnect();
      window.removeEventListener('scroll', updatePosition, true);
      window.removeEventListener('resize', updatePosition);
      updatePosition.cancel();
    };
  }, [updatePosition]);

  useEffect(() => {
    if (!wrapperRef.current) return;
    setRect(wrapperRef.current.getBoundingClientRect());
  }, [text]);

  if (!text) return children;

  return (
    <div className={styles['tooltip-wrap']} ref={wrapperRef}>
      {children}
      {rect && createPortal(<Tooltip text={text} rect={rect} extraGap={extraGap} />, document.body)}
    </div>
  );
};

export default TooltipProvider;
