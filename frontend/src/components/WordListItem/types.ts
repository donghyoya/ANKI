export interface WordListItemProps {
  KoreanWord: string;
  ForeignWord: string;
  isExpanded?: boolean;
  isHideKorean?: boolean;
  isHideForeign?: boolean;
  homographNumber: number;
  cardId: number;
}
