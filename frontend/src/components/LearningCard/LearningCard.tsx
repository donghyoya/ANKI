import { FilledCard } from '../Card/Card';
import { Card } from '@/types/Card';

import styles from './LearningCard.module.scss';
import ConjugationSection from './ConjugationSection';
import ExampleSection from './ExampleSection';
import WordSection from './WordSection';
import { useWindowSize } from '@/hooks/useWindowSize';
import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import { Menu, MenuItem } from '@/components/material-components/Menu';

import { MenuItem as MenuItemType } from '@/types/Menu';
import { useEffect } from 'react';

export interface LearningCardState {
  isRevealed: boolean;
  showDetail: boolean;
  showConjugation: boolean;
  showExample: boolean;
}

interface LearningCardProps {
  card: Card;
  className?: string;
  style?: React.CSSProperties;
  cardState: LearningCardState;
  handleReveal: () => void;
  handleShowDetail: () => void;
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
  handleShowDetail,
  toggleConjugation,
  toggleExample,
  menuItems,
  setContentHeight
}: LearningCardProps) => {
  const handleMenuClick = (e: any) => {
    e.stopPropagation();
    const menu = document.getElementById('learning-card-menu') as any;
    menu.open = !menu.open;
  };

  const { width } = useWindowSize();

  useEffect(() => {
    const contentElement = document.querySelector(`.${styles['content-container']}`);
    const height = contentElement?.scrollHeight ?? 0;
    setContentHeight(height);
  }, [cardState]);

  return (
    <FilledCard
      className={`${styles['learning-card']} ${className}`}
      ripple={false}
      onClick={handleReveal}
      style={style}
    >
      {!cardState.isRevealed && (
        <div className={styles['content-container']}>
          <span className={styles['korean-word']}>{card.wordInfo.koreanWord}</span>
          <span className={`${styles['foreign-word']} ${styles['revealed']}`}>Check Answer</span>
        </div>
      )}

      {cardState.isRevealed && !cardState.showDetail && (
        <div className={styles['content-container']}>
          <span className={styles['korean-word']}>{card.wordInfo.koreanWord}</span>
          <span className={styles['foreign-word']}>{card.wordInfo.foreignWord}</span>
        </div>
      )}

      {cardState.isRevealed && cardState.showDetail && (
        <div className={`${styles['content-container']} ${styles['detailed']}`}>
          <WordSection wordInfo={card.wordInfo} />
          <div>
            <ConjugationSection
              conjugations={card.wordInfo.inflection}
              toggleExpanded={toggleConjugation}
              isExpanded={cardState.showConjugation}
            />
            <ExampleSection
              examples={card.example}
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
              xOffset={-60}
              yOffset={4}
            >
              {menuItems.map((item) => (
                <MenuItem key={item.label} onClick={item.onClick}>
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
