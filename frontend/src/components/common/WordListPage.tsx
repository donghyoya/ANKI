'use client';

import React, { useState } from 'react';
import { useParams, notFound, useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';

import FilledButton from '@/components/material-components/FilledButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import WordList from '@/components/WordList/WordList';
import WordListCompact from '@/components/WordList/WordListCompact';

import styles from './WordListPage.module.scss';

interface WordListPageProps {
  wordType: string;
  validKeys: string[];
}

export default function WordListPage({ wordType, validKeys }: WordListPageProps) {
  const t = useTranslations();
  const { level, category } = useParams() ?? {};
  const router = useRouter();
  const { width } = useWindowSize();

  const key = level || category;

  if (!key || !validKeys.includes(key as string)) {
    notFound();
  }

  const [isExpanded, setIsExpanded] = useState(false);
  const [isHideKorean, setIsHideKorean] = useState(false);
  const [isHideForeign, setIsHideForeign] = useState(false);

  const isCompact = width < 600;
  const isLarge = width >= 1200;

  const onLearnClick = () => {
    router.push('/learning');
  };

  const toggleExpandAll = () => {
    if (!isLarge) setIsExpanded(true);
  };

  const toggleCollapseAll = () => {
    if (!isLarge) setIsExpanded(false);
  };

  const toggleHideKorean = () => {
    if (isLarge) setIsHideKorean((prev) => !prev);
  };

  const toggleHideForeign = () => {
    if (isLarge) setIsHideForeign((prev) => !prev);
  };

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
          {!isLarge && <MenuItem onClick={toggleExpandAll}>{t('expandAll')}</MenuItem>}
          {!isLarge && <MenuItem onClick={toggleCollapseAll}>{t('collapseAll')}</MenuItem>}
          <MenuItem>Sort by xxx</MenuItem>
          <MenuItem>Sort by xxx</MenuItem>
          {isLarge && (
            <MenuItem onClick={toggleHideKorean}>
              {isHideKorean ? t('showKorean') : t('hideKorean')}
            </MenuItem>
          )}
          {isLarge && (
            <MenuItem onClick={toggleHideForeign}>
              {isHideForeign ? t('showForeign') : t('hideForeign')}
            </MenuItem>
          )}
        </Menu>
      </div>
    );
  };

  const WordListComponent = !isLarge ? WordListCompact : WordList;

  const words = Array(50).fill({ KoreanWord: '안녕', ForeignWord: 'hi' });

  return (
    <div className={styles['page']}>
      <div className={styles['content']}>
        <div className={styles['header-container']}>
          <h1 className={styles.title}>{t(`${wordType}.${key}`)}</h1>
          <div className={styles['button-container']}>
            <FilledButton className={styles['learn-button']} onClick={onLearnClick}>
              {t('learn')}
            </FilledButton>
            <MenuButton />
          </div>
        </div>
        <div className={styles['list-container']}>
          {words.map((word, index) => (
            <WordListComponent
              key={index}
              KoreanWord={word.KoreanWord}
              ForeignWord={word.ForeignWord}
              isExpanded={!isLarge ? isExpanded : undefined}
              isHideKorean={isLarge ? isHideKorean : undefined}
              isHideForeign={isLarge ? isHideForeign : undefined}
            />
          ))}
        </div>
      </div>
      {isCompact && (
        <div className={styles['button-container-compact']}>
          <FilledButton className={styles['learn-button-compact']} onClick={onLearnClick}>
            {t('learn')}
          </FilledButton>
        </div>
      )}
    </div>
  );
}
