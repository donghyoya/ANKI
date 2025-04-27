import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import styles from './ConjugationSection.module.scss';
import { useTranslations } from 'next-intl';

const PredicateConjugationLabels = [
  { key: 'pastParticiple', lable: 'Past participle' },
  { key: 'connective', lable: 'Connective' },
  { key: 'seqConnect', lable: 'Sequential connective' },
  { key: 'formalPresent', lable: 'Formal polite present' }
];

const NounConjugationLabels = [
  { key: 'subjectMarker', lable: 'Subject marker' },
  { key: 'alsoTooParticle', lable: 'Also/too particle' },
  { key: 'onlyJustParticle', lable: 'Only/just particle' }
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
  const t = useTranslations();

  const conjugationLabels =
    conjugations.length > 3 ? PredicateConjugationLabels : NounConjugationLabels;

  return (
    <div className={styles['conjugations-container']}>
      <div className={styles['conjugations-header']}>
        <IconButton onClick={toggleExpanded}>
          <Icon>{isExpanded ? 'arrow_drop_up' : 'arrow_drop_down'}</Icon>
        </IconButton>
        <span className={`${styles['conjugations-header-title']}`}>
          {t('learning.conjugations')}
        </span>
      </div>
      {isExpanded && (
        <div className={styles['conjugations-list']}>
          <div className={styles['conjugation-item-container']}>
            {conjugationLabels.map((label) => (
              <div className={styles['conjugation-item']} key={label.key}>
                <span className={styles['conjugation-item-label']}>
                  {t(`learning.${label.key}`)}
                </span>
              </div>
            ))}
          </div>
          <div className={styles['conjugation-item-container']}>
            {conjugations.map((conjugation) => (
              <div className={styles['conjugation-item']} key={conjugation}>
                <span className={styles['conjugation-item-word']}>{conjugation}</span>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default ConjugationSection;
