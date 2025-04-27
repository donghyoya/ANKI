import { useTranslations } from 'next-intl';

import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import ExampleItem from './ExampleItem';

import styles from './ExampleSection.module.scss';

interface ExampleSectionProps {
  examples: string[];
  isExpanded: boolean;
  toggleExpanded: () => void;
}

const ExampleSection = ({ examples, isExpanded, toggleExpanded }: ExampleSectionProps) => {
  const t = useTranslations();

  const phraseExamples = examples
    .filter((example) => example.startsWith('<구>'))
    .map((example) => example.replace('<구>', ''));
  const sentenceExamples = examples
    .filter((example) => example.startsWith('<문장>'))
    .map((example) => example.replace('<문장>', ''));
  const conversationExamples = examples
    .filter((example) => example.startsWith('<대화>'))
    .map((example) => example.replace('<대화>', ''));

  return (
    <div className={styles['example-container']}>
      <div className={styles['example-header']}>
        <IconButton onClick={toggleExpanded}>
          <Icon>{isExpanded ? 'arrow_drop_up' : 'arrow_drop_down'}</Icon>
        </IconButton>
        <span className={styles['example-header-title']}>{t('learning.examples')}</span>
      </div>
      {isExpanded && (
        <div className={styles['example-list']}>
          {phraseExamples.length > 0 && <ExampleItem title="phrase" examples={phraseExamples} />}
          {sentenceExamples.length > 0 && (
            <ExampleItem title="sentence" examples={sentenceExamples} />
          )}
          {conversationExamples.length > 0 && (
            <ExampleItem title="conversation" examples={conversationExamples} />
          )}
        </div>
      )}
    </div>
  );
};

export default ExampleSection;
