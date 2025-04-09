import { cookies } from 'next/headers';
import { NextResponse } from 'next/server';

export async function GET() {
  const cookieStore = await cookies();
  const token = cookieStore.get('token'); // 쿠키 이름에 따라 수정

  if (!token) {
    return NextResponse.json({ error: 'No token' }, { status: 401 });
  }

  try {
    return NextResponse.json({ token: token.value });
  } catch (err: unknown) {
    if (err instanceof Error) {
      return NextResponse.json({ error: err.message }, { status: 401 });
    }
    return NextResponse.json({ error: 'Invalid token' }, { status: 401 });
  }
}
