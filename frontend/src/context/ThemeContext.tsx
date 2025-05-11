'use client';

import { createContext, useContext, useEffect, useState } from 'react';

export type Theme =
  | 'light'
  | 'dark'
  | 'lightMediumContrast'
  | 'darkMediumContrast'
  | 'lightHighContrast'
  | 'darkHighContrast';

interface ThemeContextType {
  theme: Theme;
  setTheme: (theme: Theme) => void;
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

export function ThemeProvider({ children }: { children: React.ReactNode }) {
  const [theme, setTheme] = useState<Theme>(() => {
    if (typeof window !== 'undefined') {
      return (localStorage.getItem('theme') as Theme) || 'light';
    }
    return 'light';
  });

  useEffect(() => {
    const themeClass = theme.replace(/[A-Z]/g, (match) => `-${match.toLowerCase()}`);
    document.body.classList.remove(
      'light',
      'dark',
      'light-high-contrast',
      'dark-high-contrast',
      'light-medium-contrast',
      'dark-medium-contrast'
    );
    document.body.classList.add(themeClass);
    localStorage.setItem('theme', theme);
  }, [theme]);

  return <ThemeContext.Provider value={{ theme, setTheme }}>{children}</ThemeContext.Provider>;
}

export function useTheme() {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error('useTheme must be used withn a ThemeProvider');
  }
  return context;
}
