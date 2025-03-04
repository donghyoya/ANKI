export const noSpaceLangs = ['ko', 'ja', 'zh']; // 띄어쓰기 없는 언어 리스트

export const units: Record<
  string,
  Record<
    string,
    {
      singular?: string;
      dual?: string;
      plural?: string;
      plural1?: string;
      plural2?: string;
      unit?: string;
    }
  >
> = {
  ar: {
    word: { singular: 'كلمة', dual: 'كلمتان', plural: 'كلمات' },
    min: { singular: 'دقيقة', dual: 'دقيقتان', plural: 'دقائق' },
    hour: { singular: 'ساعة', dual: 'ساعتان', plural: 'ساعات' },
    day: { singular: 'يوم', dual: 'يومان', plural: 'أيام' },
    week: { singular: 'أسبوع', dual: 'أسبوعان', plural: 'أسابيع' },
    month: { singular: 'شهر', dual: 'شهران', plural: 'أشهر' },
    year: { singular: 'سنة', dual: 'سنتان', plural: 'سنوات' }
  },
  en: {
    word: { singular: 'word', plural: 'words' },
    min: { singular: 'minute', plural: 'minutes' },
    hour: { singular: 'hour', plural: 'hours' },
    day: { singular: 'day', plural: 'days' },
    week: { singular: 'week', plural: 'weeks' },
    month: { singular: 'month', plural: 'months' },
    year: { singular: 'year', plural: 'years' }
  },
  es: {
    word: { singular: 'palabra', plural: 'palabras' },
    min: { singular: 'minuto', plural: 'minutos' },
    hour: { singular: 'hora', plural: 'horas' },
    day: { singular: 'día', plural: 'días' },
    week: { singular: 'semana', plural: 'semanas' },
    month: { singular: 'mes', plural: 'meses' },
    year: { singular: 'año', plural: 'años' }
  },
  fr: {
    word: { singular: 'mot', plural: 'mots' },
    min: { singular: 'minute', plural: 'minutes' },
    hour: { singular: 'heure', plural: 'heures' },
    day: { singular: 'jour', plural: 'jours' },
    week: { singular: 'semaine', plural: 'semaines' },
    month: { singular: 'mois', plural: 'mois' },
    year: { singular: 'an', plural: 'ans' }
  },
  id: {
    word: { singular: 'kata', plural: 'kata' },
    min: { singular: 'menit', plural: 'menit' },
    hour: { singular: 'jam', plural: 'jam' },
    day: { singular: 'hari', plural: 'hari' },
    week: { singular: 'minggu', plural: 'minggu' },
    month: { singular: 'bulan', plural: 'bulan' },
    year: { singular: 'tahun', plural: 'tahun' }
  },
  ja: {
    word: { unit: '語' },
    min: { unit: '分' },
    hour: { unit: '時間' },
    day: { unit: '日' },
    week: { unit: '週間' },
    month: { unit: 'ヶ月' },
    year: { unit: '年' }
  },
  ko: {
    word: { unit: '단어' },
    min: { unit: '분' },
    hour: { unit: '시간' },
    day: { unit: '일' },
    week: { unit: '주' },
    month: { unit: '개월' },
    year: { unit: '년' }
  },
  mn: {
    word: { unit: 'үг' },
    min: { unit: 'минут' },
    hour: { unit: 'цаг' },
    day: { unit: 'өдөр' },
    week: { unit: 'долоо хоног' },
    month: { unit: 'сар' },
    year: { unit: 'жил' }
  },
  ru: {
    word: { singular: 'слово', plural1: 'слова', plural2: 'слов' },
    min: { singular: 'минута', plural1: 'минуты', plural2: 'минут' },
    hour: { singular: 'час', plural1: 'часа', plural2: 'часов' },
    day: { singular: 'день', plural1: 'дня', plural2: 'дней' },
    week: { singular: 'неделя', plural1: 'недели', plural2: 'недель' },
    month: { singular: 'месяц', plural1: 'месяца', plural2: 'месяцев' },
    year: { singular: 'год', plural1: 'года', plural2: 'лет' }
  },
  th: {
    word: { unit: 'คำ' },
    min: { unit: 'นาที' },
    hour: { unit: 'ชั่วโมง' },
    day: { unit: 'วัน' },
    week: { unit: 'สัปดาห์' },
    month: { unit: 'เดือน' },
    year: { unit: 'ปี' }
  },
  vi: {
    word: { unit: 'từ' },
    min: { unit: 'phút' },
    hour: { unit: 'giờ' },
    day: { unit: 'ngày' },
    week: { unit: 'tuần' },
    month: { unit: 'tháng' },
    year: { unit: 'năm' }
  },
  zh: {
    word: { unit: '词' },
    min: { unit: '分钟' },
    hour: { unit: '小时' },
    day: { unit: '天' },
    week: { unit: '周' },
    month: { unit: '个月' },
    year: { unit: '年' }
  }
};
