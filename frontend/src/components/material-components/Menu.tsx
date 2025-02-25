'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdMenu } from '@material/web/menu/menu.js';
import { MdMenuItem } from '@material/web/menu/menu-item.js';

import FilledButton from './FilledButton';

export const Menu = createComponent({
  tagName: 'md-menu',
  elementClass: MdMenu,
  react: React
});

export const MenuItem = createComponent({
  tagName: 'md-menu-item',
  elementClass: MdMenuItem,
  react: React
});

export default function MenuExample() {
  const handleClick = () => {
    const menu = document.getElementById('menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  return (
    <div style={{ position: 'relative' }}>
      <FilledButton id="anchor" onClick={handleClick}>
        메뉴 열기
      </FilledButton>

      <Menu id="menu" anchor="anchor" anchorCorner="end-start" xOffset={4} yOffset={16}>
        <MenuItem>사과</MenuItem>
        <MenuItem>바나나</MenuItem>
        <MenuItem>오렌지</MenuItem>
      </Menu>
    </div>
  );
}
