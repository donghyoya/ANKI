export interface DeckCardProps {
  title: string;
  isCompleted: boolean;
  wordCount: number;
  locale: string;
  buttonLabels: {
    viewWords: string;
    learn: string;
  };
  isDifficulty: boolean;
  level?: string;
  category?: string;
  onLearn: () => void;
  onViewWords: () => void;
}
