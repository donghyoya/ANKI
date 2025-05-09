'use server';

import { cookies } from 'next/headers';

export async function setCookie(data: { name: string; value: string }) {
  const cookieStore = await cookies();

  try {
    cookieStore.set({
      name: data.name,
      value: data.value,
      httpOnly: true,
      secure: process.env.NODE_ENV === 'production',
      path: '/',
      sameSite: 'strict'
    });

    return { success: true, message: '쿠키가 성공적으로 설정되었습니다.' };
  } catch {
    return { success: false, message: '쿠키 설정 중 오류가 발생했습니다.' };
  }
}
export async function getCookie(name: string) {
  const cookieStore = await cookies();

  try {
    const cookie = cookieStore.get(name);

    if (!cookie) {
      return {
        success: false,
        message: '쿠키를 찾을 수 없습니다.',
        value: null
      };
    }

    return {
      success: true,
      message: '쿠키를 성공적으로 가져왔습니다.',
      value: cookie.value
    };
  } catch {
    return {
      success: false,
      message: '쿠키를 가져오는 중 오류가 발생했습니다.',
      value: null
    };
  }
}
