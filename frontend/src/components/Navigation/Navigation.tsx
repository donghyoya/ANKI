'use client';

import { usePathname, useRouter } from 'next/navigation';
import NavigationRail from './NavigationRail';
import TopAppBar from '@/components/Navigation/TopAppBar';
import NavigationBar from './NavigationBar';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import { IconButton, Icon } from '@/components/material-components/IconButton/IconButton';
import styles from './Navigation.module.scss';

const Navigation = ({ isLoggedIn }: { isLoggedIn: boolean }) => {
  const pathname = usePathname();
  const router = useRouter();

  const isLoginPage = pathname?.includes('/login');

  if (isLoginPage) return null;

  const destinations = [
    { icon: 'folder', label: 'difficulty' },
    { icon: 'folder', label: 'meaning' },
    isLoggedIn === false
      ? { icon: 'account_circle', label: 'guest' }
      : { icon: 'settings', label: 'settings' }
  ];

  const handleMenuClick = () => {
    const menu = document.getElementById('menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const handleDestination = (label: string) => {
    if (label === 'guest') {
      router.push('/login');
    } else {
      router.push(`/${label}`);
    }
  };

  const MenuButton = () => {
    return (
      <div style={{ position: 'relative' }}>
        <IconButton id="menu-button" onClick={handleMenuClick}>
          <Icon>more_vert</Icon>
        </IconButton>
        <Menu id="menu" anchor="menu-button" anchorCorner="end-start" xOffset={-4} yOffset={4}>
          <MenuItem>사과</MenuItem>
          <MenuItem>바나나</MenuItem>
          <MenuItem>오렌지</MenuItem>
        </Menu>
      </div>
    );
  };

  return (
    <>
      <div className={styles['mobile-view']}>
        <TopAppBar
          headline="HADA"
          leftIcon="arrow_back"
          rightIcon={<MenuButton />}
          onClickLeftIcon={() => {}}
        />
        <NavigationBar
          destinations={destinations}
          initialDestination="Difficulty"
          handleDestinationClick={handleDestination}
        />
      </div>
      <div className={styles['desktop-view']}>
        <NavigationRail destinations={destinations} isMenuEnabled initialDestination="Difficulty" />
      </div>
    </>
  );
};

export default Navigation;
