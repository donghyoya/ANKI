'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdFilledButton } from '@material/web/button/filled-button.js';

const FilledButton = createComponent({
  tagName: 'md-filled-button',
  elementClass: MdFilledButton,
  react: React,
  events: {
    onClick: 'click'
  }
});

export default FilledButton;
