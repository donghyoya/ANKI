'use server';

import { UserOption } from '@/types/schemes';
import { ServerServiceFactory } from '@/services/ServerServiceFactory';

const httpClient = ServerServiceFactory.getHttpClient();

export const getUserOption = async () => {
  const url = '/user/option';
  const response = await httpClient.get<UserOption>(url);
  return response.data;
};

export const postUserOption = async (userOption: UserOption) => {
  const url = '/user/option';
  const response = await httpClient.post<UserOption>(url, userOption);
  return response.data;
};
