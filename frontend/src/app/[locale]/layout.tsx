import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from './providers/i18n-provider';
import './layout.scss';

export default async function LocaleLayout({ children }: { children: React.ReactNode }) {
  return (
    <I18nProvider>
      <Navigation />
      <div className="layout-container-main">{children}</div>
    </I18nProvider>
  );
}
