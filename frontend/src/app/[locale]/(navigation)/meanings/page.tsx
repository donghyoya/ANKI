'use client';

import DeckListPage from '@/components/common/DeckListPage';

const meaningData = [
  { name: 'Action', key: 'action', isCompleted: true, wordCount: 123 },
  { name: 'Administration', key: 'administration', isCompleted: false, wordCount: 675 },
  { name: 'Communication', key: 'communication', isCompleted: false, wordCount: 983 },
  { name: 'Concept', key: 'concept', isCompleted: true, wordCount: 453 },
  { name: 'Culture', key: 'culture', isCompleted: false, wordCount: 754 },
  { name: 'Economy', key: 'economy', isCompleted: false, wordCount: 234 },
  { name: 'Fashion and appearance', key: 'fashion', isCompleted: false, wordCount: 876 },
  { name: 'Feeling', key: 'feeling', isCompleted: true, wordCount: 674 },
  { name: 'Food', key: 'food', isCompleted: false, wordCount: 394 },
  { name: 'Grammar and language', key: 'grammar', isCompleted: true, wordCount: 123 },
  { name: 'Home', key: 'home', isCompleted: false, wordCount: 675 },
  { name: 'Hospital', key: 'hospital', isCompleted: false, wordCount: 983 },
  { name: 'Life', key: 'life', isCompleted: true, wordCount: 453 },
  { name: 'Living', key: 'living', isCompleted: false, wordCount: 754 },
  { name: 'Nature', key: 'nature', isCompleted: false, wordCount: 234 },
  { name: 'News', key: 'news', isCompleted: false, wordCount: 876 },
  { name: 'Number', key: 'number', isCompleted: true, wordCount: 674 },
  { name: 'Personality', key: 'personality', isCompleted: false, wordCount: 394 },
  { name: 'Politics', key: 'politics', isCompleted: true, wordCount: 123 },
  { name: 'Relationships', key: 'relationships', isCompleted: false, wordCount: 675 },
  { name: 'Religion', key: 'religion', isCompleted: false, wordCount: 983 },
  { name: 'School', key: 'school', isCompleted: true, wordCount: 453 },
  { name: 'Science', key: 'science', isCompleted: false, wordCount: 754 },
  { name: 'Time', key: 'time', isCompleted: false, wordCount: 234 },
  { name: 'Transport', key: 'transport', isCompleted: false, wordCount: 876 },
  { name: 'Weather', key: 'weather', isCompleted: true, wordCount: 674 },
  { name: 'Work', key: 'work', isCompleted: false, wordCount: 394 }
];

export default function MeaningsPage() {
  return <DeckListPage title="meanings.wordsByMeanings" data={meaningData} isDifficulty={false} />;
}
