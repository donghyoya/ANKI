export interface Card {
  cardId: number;
  wordInfo: WordInfo;
  example: WordExample;
}

export interface WordInfo {
  koreanWord: string;
  foreignWord: string;
  level: string;
  languageCode: string;
  originalLanguage?: string;
  homographNumber: number;
  partsOfSpeech: string;
  pronunciation: string;
  relatedWords: string;
  inflection: string[];
}

export interface WordExample {
  phrase: string[];
  sentence: string[];
  conversation: string[];
}
