export interface DeckCardProps {
    title: string;
    isCompleted: boolean;
    wordCount: number;
    locale: string;
    buttonLabels: {
        viewWords: string;
        learn: string;
    };
    onViewWords: () => void;
    onLearn: () => void;
}