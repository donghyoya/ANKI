import Navigation from '@/components/Navigation/Navigation';
import I18nProvider from './providers/I18nProvider';
import { StoreProvider } from './providers/StoreProvider';
import './layout.scss';

export default async function LocaleLayout({ children }: { children: React.ReactNode }) {
  return (
    <StoreProvider>
      <I18nProvider>
        <Navigation />
        <main className="layout-container-main">{children}</main>
      </I18nProvider>
    </StoreProvider>
  );
}
