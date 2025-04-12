'use client';

import DeckListPage from '@/components/common/DeckListPage';
import { Deck } from '@/types/schemes';

const meaningData: Deck[] = [
  {
    category: 'Action',
    cardCounts: 123,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Administration',
    cardCounts: 675,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Communication',
    cardCounts: 983,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Concept',
    cardCounts: 453,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Culture',
    cardCounts: 754,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Economy',
    cardCounts: 234,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Fashion and appearance',
    cardCounts: 876,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Feeling',
    cardCounts: 674,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Food',
    cardCounts: 394,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Grammar and language',
    cardCounts: 123,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Home',
    cardCounts: 675,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Hospital',
    cardCounts: 983,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Living',
    cardCounts: 754,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Nature',
    cardCounts: 234,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'News',
    cardCounts: 876,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Number',
    cardCounts: 674,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Personality',
    cardCounts: 394,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Politics',
    cardCounts: 123,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Relationships',
    cardCounts: 675,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Religion',
    cardCounts: 983,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'School',
    cardCounts: 453,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Science',
    cardCounts: 754,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Time',
    cardCounts: 234,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Transport',
    cardCounts: 876,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Weather',
    cardCounts: 674,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  },
  {
    category: 'Work',
    cardCounts: 394,
    overdueRate: 0.1,
    overdueCounts: 123,
    maturityRate: 0.2,
    maturityCounts: 234
  }
];

export default function MeaningsPage() {
  return <DeckListPage decks={meaningData} category="meanings" />;
}
