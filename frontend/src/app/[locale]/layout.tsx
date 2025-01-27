import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from './providers/I18nProvider';
import './layout.scss';

export default async function LocaleLayout({ children }: { children: React.ReactNode }) {
  return (
    <I18nProvider>
      <Navigation />
      <main className="layout-container-main">{children}</main>
    </I18nProvider>
  );
}
