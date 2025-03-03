import { getFormatUnit } from './unitFormatter';

const MS_PER_MINUTE = 60 * 1000;
const MS_PER_HOUR = MS_PER_MINUTE * 60;
const MS_PER_DAY = MS_PER_HOUR * 24;
const MS_PER_WEEK = MS_PER_DAY * 7;
const MS_PER_MONTH = MS_PER_DAY * 30;
const MS_PER_YEAR = MS_PER_DAY * 365;

export function formatDuration(ms: number, locale: string): string {
  const abs = Math.abs(ms);
  const value = (duration: number) => Number(duration.toFixed(1));

  if (abs < MS_PER_MINUTE) {
    return getFormatUnit(locale, 'min', 1);
  }
  if (abs < MS_PER_HOUR) {
    const mins = value(abs / MS_PER_MINUTE);
    return getFormatUnit(locale, 'min', mins);
  }
  if (abs < MS_PER_DAY) {
    const hours = value(abs / MS_PER_HOUR);
    return getFormatUnit(locale, 'hour', hours);
  }
  if (abs < MS_PER_WEEK) {
    const days = value(abs / MS_PER_DAY);
    return getFormatUnit(locale, 'day', days);
  }
  if (abs < MS_PER_MONTH) {
    const weeks = value(abs / MS_PER_WEEK);
    return getFormatUnit(locale, 'week', weeks);
  }
  if (abs < MS_PER_YEAR) {
    const months = value(abs / MS_PER_MONTH);
    return getFormatUnit(locale, 'month', months);
  }
  const years = value(abs / MS_PER_YEAR);
  return getFormatUnit(locale, 'year', years);
}
