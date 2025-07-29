import { FilledCard } from '../Card/Card';
import { KoreanCardDetail } from '@/types/schemes';

import styles from './LearningCard.module.scss';
import classNames from 'classnames';
import ConjugationSection from './ConjugationSection';
import ExampleSection from './ExampleSection';
import WordSection from './WordSection';
import { useWindowSize } from '@/hooks/useWindowSize';
import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import { MdIconButton } from '@material/web/iconbutton/icon-button.js';

import { MenuItem as MenuItemType } from '@/types/Menu';
import { useEffect } from 'react';
import { useTranslations } from 'next-intl';

export interface LearningCardState {
  isRevealed: boolean;
  showDetail: boolean;
  showConjugation: boolean;
  showExample: boolean;
  isKoreanToForeign?: boolean;
}

interface LearningCardProps {
  card: KoreanCardDetail;
  className?: string;
  style?: React.CSSProperties;
  cardState: LearningCardState;
  handleReveal?: () => void;
  handleShowDetail?: () => void;
  toggleConjugation: () => void;
  toggleExample: () => void;
  menuItems: MenuItemType[];
  setContentHeight: (height: number) => void;
}

const LearningCard = ({
  card,
  className,
  style,
  cardState,
  handleReveal,
  // handleShowDetail,
  toggleConjugation,
  toggleExample,
  menuItems,
  setContentHeight
}: LearningCardProps) => {
  const t = useTranslations();
  const { width } = useWindowSize();

  const handleMenuClick = (e: React.MouseEvent<MdIconButton>) => {
    e.stopPropagation();
    const menu = document.getElementById('learning-card-menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  useEffect(() => {
    const contentElement = document.querySelector(`.${styles['content-container']}`);
    const height = contentElement?.scrollHeight ?? 0;
    setContentHeight(height);
  }, [cardState, setContentHeight]);

  return (
    <FilledCard
      className={classNames(styles['learning-card'], className)}
      ripple={false}
      onClick={handleReveal}
      style={style}
    >
      {!cardState.isRevealed && (
        <div className={styles['content-container']}>
          <span className={styles['korean-word']}>
            {cardState.isKoreanToForeign ? card.koreanWord : card.meanings[0].foreignWord}
          </span>
          <span className={classNames(styles['foreign-word'], styles['revealed'])}>
            {t('learning.checkAnswer')}
          </span>
        </div>
      )}

      {cardState.isRevealed && !cardState.showDetail && (
        <div className={styles['content-container']}>
          <span className={styles['korean-word']}>
            {cardState.isKoreanToForeign ? card.koreanWord : card.meanings[0].foreignWord}
          </span>
          <span className={styles['foreign-word']}>
            {cardState.isKoreanToForeign ? card.meanings[0].foreignWord : card.koreanWord}
          </span>
        </div>
      )}

      {cardState.isRevealed && cardState.showDetail && (
        <div className={classNames(styles['content-container'], styles['detailed'])}>
          <WordSection card={card} />
          <div>
            <ConjugationSection
              conjugations={card.meanings[0]?.inflection?.split(', ') ?? []}
              toggleExpanded={toggleConjugation}
              isExpanded={cardState.showConjugation}
            />
            <ExampleSection
              examples={card.meanings[0]?.exampleUsage?.trim().split('\n') ?? []}
              toggleExpanded={toggleExample}
              isExpanded={cardState.showExample}
            />
          </div>
        </div>
      )}
      {width > 600 && (
        <div style={{ position: 'absolute', top: 24, right: 24, zIndex: 1000 }}>
          <div style={{ position: 'relative', zIndex: 1000 }}>
            <IconButton id="learning-card-menu-button" onClick={handleMenuClick}>
              <Icon>more_vert</Icon>
            </IconButton>
            <Menu
              id="learning-card-menu"
              anchor="learning-card-menu-button"
              anchorCorner="end-start"
              xOffset={-160}
              yOffset={4}
              style={{ minWidth: '200px' }}
            >
              {menuItems.map((item) => (
                <MenuItem
                  key={item.label}
                  onClick={(e) => {
                    e.stopPropagation();
                    item.onClick();
                  }}
                >
                  {item.label}
                </MenuItem>
              ))}
            </Menu>
          </div>
        </div>
      )}
    </FilledCard>
  );
};

export default LearningCard;
