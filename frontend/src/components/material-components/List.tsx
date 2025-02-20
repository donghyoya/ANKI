'use client';

import React from 'react';
import { createComponent } from '@lit/react';

import { MdList } from '@material/web/list/list.js';
import { MdListItem } from '@material/web/list/list-item.js';

export const List = createComponent({
  tagName: 'md-list',
  elementClass: MdList,
  react: React
});

export const ListItem = createComponent({
  tagName: 'md-list-item',
  elementClass: MdListItem,
  react: React
});
