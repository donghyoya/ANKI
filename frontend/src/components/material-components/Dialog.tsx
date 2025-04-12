'use client';

import React from 'react';
import { createComponent } from '@lit/react';
import { MdDialog } from '@material/web/dialog/dialog.js';

const Dialog = createComponent({
    tagName: 'md-dialog',
    elementClass: MdDialog,
    react: React,
    events: {
        onCancel: 'cancel'
    },
});

export default Dialog;
