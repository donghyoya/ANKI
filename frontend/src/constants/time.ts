const SECOND_IN_MS = 1000;
const MINUTE_IN_MS = 60 * SECOND_IN_MS;
const HOUR_IN_MS = 60 * MINUTE_IN_MS;
const DAY_IN_MS = 24 * HOUR_IN_MS;

export const TIME = {
  SECOND_IN_MS,
  MINUTE_IN_MS,
  HOUR_IN_MS,
  DAY_IN_MS
} as const;
