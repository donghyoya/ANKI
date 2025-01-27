import React from 'react';
import { createComponent } from '@lit/react';
import { MdRipple } from '@material/web/ripple/ripple.js';

export const Ripple = createComponent({
  tagName: 'md-ripple',
  elementClass: MdRipple,
  react: React
});

export default function RippleExample() {
  return (
    <div
      style={{
        alignItems: 'center',
        borderRadius: '24px',
        display: 'flex',
        height: '64px',
        justifyContent: 'center',
        outline: '1px solid var(--md-sys-color-outline)',
        padding: '16px',
        position: 'relative',
        width: '64px'
      }}
    >
      <Ripple />
    </div>
  );
}
