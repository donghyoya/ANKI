import { WordExample } from '@/types/Card';
import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';

import styles from './ExampleSection.module.scss';

import { capitalize } from '@/utils/capitalize';

interface ExampleSectionProps {
  examples: WordExample;
  isExpanded: boolean;
  toggleExpanded: () => void;
}

const ExampleSection = ({ examples, isExpanded, toggleExpanded }: ExampleSectionProps) => {
  return (
    <div className={styles['example-container']}>
      <div className={styles['example-header']}>
        <IconButton onClick={toggleExpanded}>
          <Icon>{isExpanded ? 'arrow_drop_up' : 'arrow_drop_down'}</Icon>
        </IconButton>
        <span className={styles['example-header-title']}>Examples</span>
      </div>
      {isExpanded && (
        <div className={styles['example-list']}>
          {Object.keys(examples).map((key, index) => (
            <div className={styles['example-item-container']} key={index}>
              <div className={styles['example-item']} key={index}>
                <span className={styles['example-item-label']}>{capitalize(key)}</span>
              </div>
              <span className={styles['example-item-list']}>
                {examples[key as keyof WordExample].map((example, index) => (
                  <div className={styles['example-item-list-text']} key={index}>
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
