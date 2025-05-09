'use server';

import { UserOption } from '@/types/schemes';
import { apiRequest } from './utils';

const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getUserOption = async (token: string) => {
  const url = `${endpoint}/user/option`;
  const response = await apiRequest<UserOption>({ url, token });
  return response;
};

export const postUserOption = async (userOption: UserOption, token: string) => {
  const url = `${endpoint}/user/option`;
  const response = await apiRequest<UserOption>({ url, token, method: 'POST', body: userOption });
  return response;
};
