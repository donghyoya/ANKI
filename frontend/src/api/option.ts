'use server';

import { ExceptionResponse, UserOption } from '@/types/schemes';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserOption = async (token: string) => {
  const url = `${endpoint}/user/option`;
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
    throw new Error('Failed to fetch user option');
  }
};

export const postUserOption = async (userOption: UserOption, token: string) => {
  const url = `${endpoint}/user/option`;
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(userOption)
  });

  if (response.ok) {
    return response.json();
  } else {
    throw new Error('Failed to post user option');
  }
};
