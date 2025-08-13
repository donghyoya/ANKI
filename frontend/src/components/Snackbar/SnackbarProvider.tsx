'use client';

import React, { createContext, useContext, useState, useCallback } from 'react';
import Snackbar from './Snackbar';
import { createPortal } from 'react-dom';
import { AnimatePresence } from 'motion/react';

interface SnackbarContextType {
  showSnackbar: (
    text: string,
    options?: {
      actionLabel?: string;
      onAction?: () => void;
      closable?: boolean;
    }
  ) => void;
}

const SnackbarContext = createContext<SnackbarContextType | null>(null);

export const useSnackbar = () => {
  const context = useContext(SnackbarContext);
  if (!context) throw new Error('useSnackbar must be used within a SnackbarProvider');
  return context;
};

export const SnackbarProvider = ({ children }: { children: React.ReactNode }) => {
  const [text, setText] = useState('');
  const [actionLabel, setActionLabel] = useState<string | undefined>();
  const [onAction, setOnAction] = useState<(() => void) | undefined>();
  const [closable, setClosable] = useState(false);
  const [visible, setVisible] = useState(false);

  const showSnackbar = useCallback(
    (
      newText: string,
      options?: {
        actionLabel?: string;
        onAction?: () => void;
        closable?: boolean;
      }
    ) => {
      setText(newText);
      setActionLabel(options?.actionLabel);
      setOnAction(() => options?.onAction);
      setClosable(!!options?.closable);
      setVisible(true);

      // closable이 false일 경우만 자동 사라짐
      if (!options?.closable) {
        setTimeout(() => {
          setVisible(false);
        }, 3000);
      }
    },
    []
  );

  const handleClose = () => setVisible(false);

  return (
    <SnackbarContext.Provider value={{ showSnackbar }}>
      {children}
      {createPortal(
        <AnimatePresence>
          {visible && (
            <Snackbar
              key="snackbar"
              text={text}
              actionLabel={actionLabel}
              onAction={() => {
                onAction?.();
                setVisible(false);
              }}
              closable={closable}
              onRequestClose={handleClose}
            />
          )}
        </AnimatePresence>,
        document.body
      )}
    </SnackbarContext.Provider>
  );
};
