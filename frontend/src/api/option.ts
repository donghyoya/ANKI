'use server';

import { ExceptionResponse, UserOption } from '@/types/schemes';

const endpoint = process.env.NEXT_PUBLIC_API_URL;

export const getUserOption = async (token: string) => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};

export const postUserOption = async (userOption: UserOption, token: string) => {
  const url = `${endpoint}/option`;
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const data = await response.json();

  if (response.ok) {
    return data as UserOption;
  } else {
    return data as ExceptionResponse;
  }
};
