const endpoint = process.env.NEXT_PUBLIC_SERVER;

export const getToken = async (authenticationToken: string) => {
  const url = `${endpoint}/auth/token`;
  console.log('authenticationToken', authenticationToken);

  const response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${authenticationToken}`,
      'Content-Type': 'application/json'
    }
  });

  if (response.ok) {
    const data = response.json();
    return data;
  }
};
