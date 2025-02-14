import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import styles from './ConjugationSection.module.scss';

const CONJUGATION_LABELS = [
  'Past participle',
  'Connective',
  'Sequential connective',
  'Formal polite present'
];

interface ConjugationSectionProps {
  conjugations: string[];
  toggleExpanded: () => void;
  isExpanded: boolean;
}

const ConjugationSection = ({
  conjugations,
  toggleExpanded,
  isExpanded
}: ConjugationSectionProps) => {
  return (
    <div className={styles['conjugations-container']}>
      <div className={styles['conjugations-header']}>
        <IconButton onClick={toggleExpanded}>
          <Icon>{isExpanded ? 'arrow_drop_up' : 'arrow_drop_down'}</Icon>
        </IconButton>
        <span className={`${styles['conjugations-header-title']}`}>Conjugations</span>
      </div>
      {isExpanded && (
        <div className={styles['conjugations-list']}>
          {conjugations.map((conjugation, index) => (
            <div className={styles['conjugation-item']} key={index}>
              <span className={styles['conjugation-item-label']}>{CONJUGATION_LABELS[index]}</span>
              <span className={styles['conjugation-item-word']}>{conjugation}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ConjugationSection;
