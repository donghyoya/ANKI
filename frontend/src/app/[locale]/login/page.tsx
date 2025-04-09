'use client';

function getGoogleLoginLink() {
  const server = process.env.NEXT_PUBLIC_SERVER;
  const endpoint = process.env.NEXT_PUBLIC_AUTH_ENDPOINT;
  const redirect = process.env.NEXT_PUBLIC_REDIRECT_URI;
  const myhost = process.env.NEXT_PUBLIC_MY_HOST;

  return `${server}/${endpoint}?redirect_uri=${encodeURIComponent(`${myhost}/${redirect}`)}`;
}

export default function LoginPage() {
  const link = getGoogleLoginLink();

  return (
    <div className="max-w-xl mx-auto my-12 p-6 bg-gray-100 rounded-lg shadow-md">
      <h1 className="text-2xl font-bold text-gray-800 mb-6">로그인 페이지</h1>

      <h3 className="text-lg font-semibold text-gray-700 mb-4">
        <a href={link} className="text-blue-600 hover:underline">
          {/* #중요# */}
          구글 로그인
        </a>
      </h3>

      <p className="text-gray-600 break-all mb-6">{link}</p>

      <p className="text-gray-700 leading-relaxed">
        이 링크로 접근하게 되면 소셜 로그인 절차가 <strong>spring oauth2-client</strong>
        라이브러리에 의해서 수행되게 됩니다. 일련의 로그인 절차를 수행한 이후
        <code className="bg-gray-200 px-1 rounded">/auth/redirect</code>로 다시 되돌아오게 됩니다.
        <br />
        현재는 <code className="bg-gray-200 px-1 rounded">localhost:3000/auth/redirect</code>만
        수행됩니다.
      </p>
    </div>
  );
}
