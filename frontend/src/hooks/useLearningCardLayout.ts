import { useEffect, useState } from 'react';

const COMPACT_UI_HEIGHT = 296 + 7;
const MEDIUM_UI_HEIGHT = 152 + 7;
const EXPANDED_UI_HEIGHT = 152 + 20 + 7;

interface LearningCardLayoutProps {
  contentHeight: number;
  cardWidth: number;
}

const useLearningCardLayout = ({ contentHeight, cardWidth }: LearningCardLayoutProps) => {
  const [cardStyle, setCardStyle] = useState({});

  useEffect(() => {
    const handleResize = () => {
      let uiHeight = 0;
      if (window.innerWidth < 600) {
        uiHeight = COMPACT_UI_HEIGHT;
      } else if (window.innerWidth < 840) {
        uiHeight = MEDIUM_UI_HEIGHT;
      } else {
        uiHeight = EXPANDED_UI_HEIGHT;
      }

      if (window.innerWidth > 1200) {
        setCardStyle({ aspectRatio: 16 / 9, height: 'auto' });
      } else if (contentHeight > window.innerHeight - uiHeight) {
        setCardStyle({ height: contentHeight + 100 });
      } else if (cardWidth + uiHeight > window.innerHeight) {
        setCardStyle({ height: window.innerHeight - uiHeight });
      } else {
        setCardStyle({ height: cardWidth });
      }
    };

    handleResize();
    window.addEventListener('resize', handleResize);

    return () => window.removeEventListener('resize', handleResize);
  }, [contentHeight, cardWidth]);

  return cardStyle;
};

export default useLearningCardLayout;
