import { cookies } from 'next/headers';

export default async function AuthTestServerPage() {
  const cookieStore = await cookies();
  const token = cookieStore.get('token')?.value;

  if (!token) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
        <div className="bg-white p-8 rounded-lg shadow-md text-center w-full max-w-md">
          <h1 className="text-2xl font-bold text-red-500">로그인이 필요합니다.</h1>
        </div>
      </div>
    );
  }

  const res = await fetch(`${process.env.NEXT_PUBLIC_SERVER}/auth-test`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`
    },
    cache: 'no-store' // 항상 최신 데이터
  });

  if (res.status === 401) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
        <div className="bg-white p-8 rounded-lg shadow-md text-center w-full max-w-md">
          <h1 className="text-2xl font-bold text-yellow-500">
            토큰이 만료되었거나 인증이 필요합니다.
          </h1>
        </div>
      </div>
    );
  }

  if (!res.ok) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
        <div className="bg-white p-8 rounded-lg shadow-md text-center w-full max-w-md">
          <h1 className="text-2xl font-bold text-red-600">서버 에러 발생</h1>
        </div>
      </div>
    );
  }

  const data = await res.json();

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="bg-white p-8 rounded-lg shadow-md text-center w-full max-w-md">
        <h1 className="text-2xl font-bold text-green-600">Welcome, {data.username}!</h1>
      </div>
    </div>
  );
}
