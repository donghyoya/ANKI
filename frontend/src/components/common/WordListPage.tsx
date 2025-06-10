'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';

import FilledButton from '@/components/material-components/FilledButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import WordList from '@/components/WordList/WordList';
import WordListCompact from '@/components/WordList/WordListCompact';
import { KoreanCardDetail } from '@/types/schemes';
import styles from './WordListPage.module.scss';
import { getCategoryType } from '@/types/Category';
import { camelCase } from 'lodash';

export default function WordListPage({
  wordList,
  category
}: {
  wordList: KoreanCardDetail[];
  category: string;
}) {
  const t = useTranslations();
  const router = useRouter();
  const { width } = useWindowSize();

  const [isExpanded, setIsExpanded] = useState(false);
  const [isHideKorean, setIsHideKorean] = useState(false);
  const [isHideForeign, setIsHideForeign] = useState(false);

  const isCompact = width < 600;
  const isLarge = width >= 1200;

  const WordListComponent = !isLarge ? WordListCompact : WordList;

  const title =
    getCategoryType(category) === 'difficulty'
      ? `difficulty.${category}`
      : `meaning.${camelCase(category)}`;

  const onLearnClick = () => {
    router.push(`/learning/${category}`);
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

  return (
    <div className={styles['page']}>
      <div className={styles['content']}>
        <div className={styles['header-container']}>
          <h1 className={styles.title}>{t(title)}</h1>
          <div className={styles['button-container']}>
            <FilledButton className={styles['learn-button']} onClick={onLearnClick}>
              {t('learn')}
            </FilledButton>
            <MenuButton />
          </div>
        </div>
        <div className={styles['list-container']}>
          {wordList.map((word, index) => (
            <WordListComponent
              key={index}
              KoreanWord={word.koreanWord}
              ForeignWord={word.meanings[0].foreignWord}
              isExpanded={!isLarge && isExpanded}
              isHideKorean={isLarge && isHideKorean}
              isHideForeign={isLarge && isHideForeign}
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
