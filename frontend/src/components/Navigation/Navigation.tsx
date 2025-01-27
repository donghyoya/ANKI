'use client';

import { useState } from 'react';

import NavigationRail from './NavigationRail';
import NavigationDrawer from './NavigationDrawer';
import TopAppBar from '@/components/Navigation/TopAppBar';
import NavigationBar from './NavigationBar';
import { Menu, MenuItem } from '@/components/Menu';
import { IconButton, Icon } from '@/components/IconButton/IconButton';
import styles from './Navigation.module.scss';

const Navigation = () => {
  const destinations = [
    { icon: 'folder', label: 'Difficulty' },
    { icon: 'folder', label: 'Meanings' },
    { icon: 'settings', label: 'Settings' }
  ];

  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const handleMenuClick = (e: any) => {
    const menu = document.getElementById('menu') as any;
    menu.open = !menu.open;
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
        <NavigationBar destinations={destinations} initialDestination="Difficulty" />
      </div>
      <div className={styles['desktop-view']}>
        {isDrawerOpen && (
          <>
            <div className={styles['navigation-scrim']} onClick={toggleDrawer} />
            <NavigationDrawer
              destinations={destinations}
              initialDestination="Difficulty"
              toggleDrawer={toggleDrawer}
            />
          </>
        )}
        {!isDrawerOpen && (
          <NavigationRail
            destinations={destinations}
            isMenuEnabled
            initialDestination="Difficulty"
            toggleDrawer={toggleDrawer}
          />
        )}
      </div>
    </>
  );
};

export default Navigation;
