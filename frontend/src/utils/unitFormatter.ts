import { units, noSpaceLangs } from '@/constants/units';

export const getFormatUnit = (
  locale: string,
  unit: string,
  count: number,
  useComma: boolean = false
): string => {
  const formattedCount = useComma ? count.toLocaleString(locale) : count;

  const localeUnits = units[locale];
  if (!localeUnits) return '';

  const unitData = localeUnits[unit];
  if (!unitData) return '';

  // 단수 / 복수 규칙 없는 언어
  if ('unit' in unitData)
    return noSpaceLangs.includes(locale)
      ? `${formattedCount}${unitData.unit}`
      : `${formattedCount} ${unitData.unit}`;

  // 아랍어
  if (locale === 'ar') {
    if (count === 1 || (count >= 11 && count <= 99))
      return `${formattedCount} ${unitData.singular}`; // 단수형
    else if (count === 2)
      return `${formattedCount} ${unitData.dual}`; // 쌍수형
    else return `${formattedCount} ${unitData.plural}`; // 복수형
  }

  // 러시아어
  if (locale === 'ru') {
    const lastDigit = count % 10;
    const lastTwoDigit = count % 100;

    if (lastTwoDigit >= 11 && lastTwoDigit <= 19) return `${formattedCount} ${unitData.plural2}`; // 11~19 예외처리 (복수형2)
    if (lastDigit === 1) return `${formattedCount} ${unitData.singular}`; // 1: 단수형
    if (lastDigit >= 2 && lastDigit <= 4) return `${formattedCount} ${unitData.plural1}`; // 2~4: 복수형1
    return `${formattedCount} ${unitData.plural2}`; // 5~9, 0: 복수형2
  }

  return count === 1
    ? `${formattedCount} ${unitData.singular}`
    : `${formattedCount} ${unitData.plural}`;
};
