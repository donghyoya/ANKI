import { NextRequest, NextResponse } from 'next/server';
import { serialize } from 'cookie';

export async function POST(req: NextRequest) {
  const { token } = await req.json(); // 클라이언트에서 token 받음

  if (!token) {
    return NextResponse.json({ message: 'No token provided' }, { status: 400 });
  }

  // 토큰을 쿠키에 저장
  const response = NextResponse.json({ message: 'Token set' });

  response.headers.set(
    // 🔥 핵심 변경
    'Set-Cookie',
    serialize('token', token, {
      httpOnly: true,
      path: '/',
      sameSite: 'lax',
      secure: process.env.NODE_ENV === 'production',
      maxAge: 60 * 60 // 1시간
    })
  );

  return response;
}
