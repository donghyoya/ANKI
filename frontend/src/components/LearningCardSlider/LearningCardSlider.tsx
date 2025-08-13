import styles from './LearningCardSlider.module.scss';
import { AnimatePresence, motion } from 'motion/react';
import { useEffect } from 'react';

interface LearningCardSliderProps {
  direction: 1 | -1;
  children: React.ReactNode;
  index: number; // key prop 추가
}

const LearningCardSlider = ({ direction, children, index }: LearningCardSliderProps) => {
  const variants = {
    initial: {
      x: direction * 500,
      opacity: 0
    },
    animate: {
      x: 0,
      opacity: 1
    },
    exit: (direction: number) => ({
      x: direction * -500,
      opacity: 0
    })
  };

  useEffect(() => {
    console.log('index', index);
  }, [index]);

  return (
    <div className={styles.container}>
      <AnimatePresence custom={direction} mode="sync">
        <motion.div
          key={index}
          variants={variants}
          custom={direction}
          initial="initial"
          animate="animate"
          exit="exit"
          transition={{ duration: 0.5, ease: 'easeInOut' }}
          className={styles['card-container']}
        >
          {children}
        </motion.div>
      </AnimatePresence>
    </div>
  );
};

export default LearningCardSlider;
