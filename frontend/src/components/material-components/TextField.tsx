'use client';

import React from 'react';
import { createComponent } from '@lit/react';

import { MdFilledTextField } from '@material/web/textfield/filled-text-field.js';
import { MdOutlinedTextField } from '@material/web/textfield/outlined-text-field.js';

export const FilledTextButton = createComponent({
    tagName: 'md-filled-text-field',
    elementClass: MdFilledTextField,
    react: React
});

export const OutlinedTextField = createComponent({
    tagName: 'md-outlined-text-field',
    elementClass: MdOutlinedTextField,
    react: React,
    events: {
        onChange: 'change'
    }
});