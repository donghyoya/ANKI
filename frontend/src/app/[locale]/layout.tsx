import I18nProvider from '../../providers/I18nProvider';

export default async function LocaleLayout({ children }: { children: React.ReactNode }) {
  return <I18nProvider>{children}</I18nProvider>;
}
