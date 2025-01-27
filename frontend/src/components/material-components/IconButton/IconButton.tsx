'use client';

import React from 'react';
import { createComponent } from '@lit/react';

import { MdIcon } from '@material/web/icon/icon.js';
import { MdIconButton } from '@material/web/iconbutton/icon-button.js';
import { MdFilledIconButton } from '@material/web/iconbutton/filled-icon-button.js';
import { MdFilledTonalIconButton } from '@material/web/iconbutton/filled-tonal-icon-button.js';
import { MdOutlinedIconButton } from '@material/web/iconbutton/outlined-icon-button.js';

export const Icon = createComponent({
  tagName: 'md-icon',
  elementClass: MdIcon,
  react: React
});

export const IconButton = createComponent({
  tagName: 'md-icon-button',
  elementClass: MdIconButton,
  react: React
});

export const FilledIconButton = createComponent({
  tagName: 'md-filled-icon-button',
  elementClass: MdFilledIconButton,
  react: React
});

export const FilledTonalIconButton = createComponent({
  tagName: 'md-filled-tonal-icon-button',
  elementClass: MdFilledTonalIconButton,
  react: React
});

export const OutlinedIconButton = createComponent({
  tagName: 'md-outlined-icon-button',
  elementClass: MdOutlinedIconButton,
  react: React
});
