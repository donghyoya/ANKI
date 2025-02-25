'use client';

import React from 'react';
import { useParams, notFound } from 'next/navigation';
import { useTranslations } from 'next-intl';
import FilledButton from '@/components/material-components/FilledButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import WordList from '@/components/WordList/WordList';
import styles from './Level.module.scss';
import { Menu, MenuItem } from '@/components/material-components/Menu';

const difficulty = ['beginner', 'intermediate', 'advanced'];

export default function DifficultyWordsPage() {
  const t = useTranslations();
  const { level } = useParams() ?? {};

  if (!difficulty.includes(level as string)) {
    notFound();
  }

  const handleMenuClick = () => {
    const menu = document.getElementById('word-list-more') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const MenuButton = () => {
    return (
      <div style={{ position: 'relative' }}>
        <IconButton onClick={handleMenuClick}>
          <Icon>more_vert</Icon>
        </IconButton>
        <Menu
          id="word-list-more"
          anchor="menu-button"
          xOffset={-160}
          yOffset={47}
          style={{ minWidth: '200px' }}
        >
          <MenuItem>Sort by xxx</MenuItem>
          <MenuItem>Sort by xxx</MenuItem>
          <MenuItem>{t('hideKorean')}</MenuItem>
          <MenuItem>{t('hideForeign')}</MenuItem>
        </Menu>
      </div>
    );
  };

  return (
    <div className={styles['page']}>
      <div className={styles['content']}>
        <div className={styles['header-container']}>
          <h1 className={styles.title}>{t(`level.${level}`)}</h1>
          <div className={styles['button-container']}>
            <FilledButton className={styles['learn-button']}>{t('learn')}</FilledButton>
            <MenuButton />
          </div>
        </div>
        <div className={styles['list-container']}>
          <WordList KoreanWord="안녕" ForeignWord="hi" />
          <WordList KoreanWord="안녕" ForeignWord="hi" />
        </div>
      </div>
    </div>
  );
}
