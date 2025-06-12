import { UserOption } from '@/types/schemes';
import { DUMMY_USER_OPTION } from '@/utils/dummyData';

export const getUserOption = async () => {
  return new Promise<UserOption>((resolve) => {
    setTimeout(() => {
      const data = DUMMY_USER_OPTION;
      console.log('mockGetUserOption:', data);
      resolve(data);
    }, 500);
  });
};

export const postUserOption = async (userOption: UserOption) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const data = userOption;
      console.log('mockPostUserOption:', data);
      resolve(data);
    }, 500);
  });
};
