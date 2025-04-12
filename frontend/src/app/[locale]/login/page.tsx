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
    <div>
      <h1>로그인 페이지</h1>

      <h3>
        <a href={link}>
          {/* #중요# */}
          구글 로그인
        </a>
      </h3>

      <p>{link}</p>

      <p>
        이 링크로 접근하게 되면 소셜 로그인 절차가 <strong>spring oauth2-client</strong>
        라이브러리에 의해서 수행되게 됩니다. 일련의 로그인 절차를 수행한 이후
        <code>/auth/redirect</code>로 다시 되돌아오게 됩니다.
        <br />
        현재는 <code>localhost:3000/auth/redirect</code>만 수행됩니다.
      </p>
    </div>
  );
}
