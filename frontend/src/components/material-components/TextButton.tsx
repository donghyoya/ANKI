'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdTextButton } from '@material/web/button/text-button.js';

const TextButton = createComponent({
  tagName: 'md-text-button',
  elementClass: MdTextButton,
  react: React,
  events: {
    onClick: 'click'
  }
});

export default TextButton;
