import { cookies } from 'next/headers';

export class CookieService {
  // 쿠키 설정
  async set(name: string, value: string): Promise<void> {
    const cookieStore = await cookies();

    try {
      cookieStore.set({
        name: name,
        value: value,
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
        path: '/',
        sameSite: 'strict'
      });

      console.log('CookieService.set 쿠키 설정 성공');
      return;
    } catch {
      console.log('CookieService.set 쿠키 설정 실패');
      return;
    }
  }

  // 쿠키 조회
  async get(name: string): Promise<string | null> {
    const cookieStore = await cookies();

    try {
      const cookie = cookieStore.get(name);

      if (!cookie) {
        console.log('CookieService.get 쿠키 없음');
        return null;
      }

      console.log('CookieService.get 쿠키 조회 성공', cookie.value);
      return cookie.value;
    } catch {
      console.log('CookieService.get 쿠키 조회 실패');
      return null;
    }
  }

  // 쿠키 삭제
  async remove(name: string): Promise<void> {
    const cookieStore = await cookies();

    try {
      cookieStore.delete(name);
      console.log('CookieService.remove', name, '쿠키 삭제 성공');
    } catch {
      console.log('CookieService.remove', name, '쿠키 삭제 실패');
    }
  }
}
