'use client';

import React, { useEffect, useRef, useState } from 'react';
import { useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';
import { useWindowSize } from '@/hooks/useWindowSize';

import FilledButton from '@/components/material-components/FilledButton';
import { Icon, IconButton } from '@/components/material-components/IconButton/IconButton';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import WordListItemDesktop from '@/components/WordListItem/WordListItemDesktop';
import WordListItemMobile from '@/components/WordListItem/WordListItemMobile';
import { KoreanCardWithForeignWords } from '@/types/schemes';
import styles from './WordListPage.module.scss';
import { getCategoryType } from '@/types/Category';
import { camelize } from 'humps';
import { SpinnerCircular } from 'spinners-react';

export default function WordListPage({
  wordList,
  category,
  onLoadMore,
  isLoading,
  hasMore
}: {
  wordList: KoreanCardWithForeignWords[];
  category: string;
  onLoadMore: () => void;
  isLoading: boolean;
  hasMore: boolean;
}) {
  const t = useTranslations();
  const router = useRouter();
  const { width } = useWindowSize();

  const [isExpanded, setIsExpanded] = useState(false);
  const [isHideKorean, setIsHideKorean] = useState(false);
  const [isHideForeign, setIsHideForeign] = useState(false);

  const isCompact = width < 600;
  const isLarge = width >= 1200;

  const WordListItem = !isLarge ? WordListItemMobile : WordListItemDesktop;

  const title =
    getCategoryType(category) === 'LEVEL'
      ? `difficulty.${category}`
      : `meaning.${camelize(category)}`;

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

  const loadTriggerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          console.log('load more');
          onLoadMore();
        }
      });
    });

    if (loadTriggerRef.current) {
      observer.observe(loadTriggerRef.current);
    }

    return () => observer.disconnect();
  }, [onLoadMore]);

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
        <div className={`${styles['list-container']} .word-list`}>
          {wordList.map((word, index) => (
            <WordListItem
              key={index}
              KoreanWord={word.koreanWord}
              ForeignWord={word.foreignWords[0]}
              homographNumber={+word.homographNumber + 1}
              isExpanded={!isLarge && isExpanded}
              isHideKorean={isLarge && isHideKorean}
              isHideForeign={isLarge && isHideForeign}
              cardId={word.cardId}
            />
          ))}

          {hasMore && (
            <div className={styles['load-more']} ref={loadTriggerRef}>
              {isLoading ? (
                <div className={styles['loading-spinner']}>
                  <SpinnerCircular />
                </div>
              ) : (
                'Load More'
              )}
            </div>
          )}
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
