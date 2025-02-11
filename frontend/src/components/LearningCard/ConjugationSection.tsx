import { useState } from 'react';

import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import styles from './ConjugationSection.module.scss';

const conjugations = [
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
          <Icon>play_arrow</Icon>
        </IconButton>
        <span className="md-typescale-title-small">Conjugations</span>
      </div>
      {isExpanded && (
        <div className={styles['conjugations-list']}>
          {conjugations.map((conjugation, index) => (
            <div className={styles['conjugation-item']} key={index}>
              <span className={`${styles['conjugation-item-label']} md-typescale-label-medium`}>
                {conjugations[index]}
              </span>
              <span className="md-typescale-label-medium">{conjugation}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ConjugationSection;
