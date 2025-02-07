'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdDialog } from '@material/web/dialog/dialog.js';

const Dialog = createComponent({
    tagName: 'md-dialog',
    elementClass: MdDialog,
    react: React,
});

export default Dialog;
