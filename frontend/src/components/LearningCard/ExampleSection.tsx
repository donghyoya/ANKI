import { useState } from 'react';

import { WordExample } from '@/types/Card';
import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';

import styles from './ExampleSection.module.scss';

import { capitalize } from '@/utils/capitalize';

const ExampleSection = ({ examples }: { examples: WordExample }) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const toggleExpanded = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <div className={styles['example-container']}>
      <div className={styles['example-header']}>
        <IconButton onClick={toggleExpanded}>
          <Icon>play_arrow</Icon>
        </IconButton>
        <span className="md-typescale-title-small">Examples</span>
      </div>
      {isExpanded && (
        <div className={styles['example-list']}>
          {Object.keys(examples).map((key, index) => (
            <div className={styles['example-item-container']} key={index}>
              <div className={styles['example-item']} key={index}>
                <span className={`${styles['example-item-label']} md-typescale-label-medium`}>
                  {capitalize(key)}
                </span>
              </div>
              <span className={`md-typescale-body-small`}>
                {examples[key as keyof WordExample].map((example, index) => (
                  <div className={styles['example-item']} key={index}>
                    <span key={index}>{example}</span>
                  </div>
                ))}
              </span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ExampleSection;
