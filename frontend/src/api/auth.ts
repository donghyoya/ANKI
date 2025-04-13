export function getGoogleLoginLink() {
  const server = process.env.NEXT_PUBLIC_SERVER;
  const endpoint = process.env.NEXT_PUBLIC_AUTH_ENDPOINT;
  const redirect = process.env.NEXT_PUBLIC_REDIRECT_URI;
  const myhost = process.env.NEXT_PUBLIC_MY_HOST;

  return `${server}/${endpoint}?redirect_uri=${encodeURIComponent(`${myhost}/${redirect}`)}`;
}
