const wordUnits: Record<string, (count: number) => string> = {
  ar: (count) => {
    if (count === 1 || (count >= 11 && count <= 99))
      return 'كلمة'; // 단수형
    else if (count === 2)
      return 'كلمتان'; // 쌍수형
    else return 'كلمات'; // 복수형
  },
  en: (count) => (count === 1 ? 'word' : 'words'),
  es: (count) => (count === 1 ? 'palabra' : 'palabras'),
  fr: (count) => (count === 1 ? 'mot' : 'mots'),
  id: () => 'kata',
  ja: () => '語',
  ko: () => '단어',
  mn: () => 'үг',
  ru: (count) => {
    if (count % 100 >= 11 && count % 100 <= 19) return 'слов'; // 11~19 예외처리 (복수형2)

    const last_digit = count % 10; // 마지막 자리 숫자

    if (last_digit === 1)
      return 'слово'; // 1: 단수형
    else if (last_digit >= 2 && last_digit <= 4)
      return 'слова'; // 2~4: 복수형1
    else return 'слов'; // 5~9, 0: 복수형 2
  },
  th: () => 'คำ',
  vi: () => 'từ',
  zh: () => '词'
};

const noSpaceLangs = ['ko', 'ja', 'zh'];

export const getWordCount = (count: number, locale: string): string => {
  const formattedCount = count.toLocaleString(locale);
  const wordUnit = wordUnits[locale] ? wordUnits[locale](count) : 'words';
  const noSpace = noSpaceLangs.includes(locale);

  return noSpace ? `${formattedCount}${wordUnit}` : `${formattedCount} ${wordUnit}`;
};
