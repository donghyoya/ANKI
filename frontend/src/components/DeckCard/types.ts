import { Deck } from '@/types/schemes';

export interface DeckCardProps {
  deck: Deck;
  title: string;
  isCompleted: boolean;
  locale: string;
  buttonLabels: {
    viewWords: string;
    learn: string;
  };
  onLearn: () => void;
  onViewWords: () => void;
}
