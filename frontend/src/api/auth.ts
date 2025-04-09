const endpoint = process.env.NEXT_PUBLIC_API_URL;

export const login = async () => {
  const url = `/api/auth/oauth2/google`;
  const response = await fetch(url);
  const data = await response.json();
  console.log(data);
};

export const handleLogin = () => {
  const width = 500;
  const height = 600;
  const left = window.screenX + (window.outerWidth - width) / 2;
  const top = window.screenY + (window.outerHeight - height) / 2;

  return new Promise((resolve) => {
    window.open(
      `${endpoint}/oauth2/authorization/google`,
      'Google 로그인',
      `width=${width},height=${height},left=${left},top=${top}`
    );

    window.addEventListener(
      'message',
      function (event) {
        resolve(event.data);
      },
      { once: true }
    );
  });
};

export const checkLogin = async () => {
  const url = `${endpoint}/api/auth/checkLogin.do`;
  const response = await fetch(url, {
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    }
  });
  const data = await response.json();
  console.log(data);
  return data;
};

export const logout = async () => {
  const url = `${endpoint}/api/auth/logout.do`;
  const response = await fetch(url, {
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json; charset=UTF-8'
    }
  });
  const data = await response.json();
  console.log(data);
  return data;
};

// 토큰 기반 로그인 구현
export const tokenLoginTest = async (token: string) => {
  if (!token) return;
  const res = await fetch(`${process.env.NEXT_PUBLIC_SERVER}/auth-test`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });

  if (res.status === 401) {
    throw new Error('로그인이 필요합니다.');
  }

  if (!res.ok) {
    throw new Error('Failed to fetch');
  }

  const data = await res.json();
  return data;
};
