'use server';

import { ExceptionResponse, UserOption } from '@/types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_URL;

const requestOptions: RequestInit = {
  headers: {
    accept: 'application/json;charset=UTF-8',
    'Content-Type': 'application/json'
  },
  credentials: 'include',
  cache: 'no-store'
};

export const getUserOption = async () => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, requestOptions);
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};

export const postUserOption = async (userOption: UserOption) => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, {
    ...requestOptions,
    method: 'POST',
    body: JSON.stringify(userOption)
  });
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};
