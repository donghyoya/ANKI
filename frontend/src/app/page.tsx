/** @format */

'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdFilledButton } from '@material/web/button/filled-button.js';

// button example
const FilledButton = createComponent({
  tagName: 'md-filled-button',
  elementClass: MdFilledButton,
  react: React,
  events: {
    onClick: 'click'
  }
});

export default function Home() {
  return (
    <div>
      <FilledButton>Hello</FilledButton>
    </div>
  );
}
