'use client';

import { getGoogleLoginLink } from '@/api/auth';

import Link from 'next/link';

export default function LoginPage() {
  const link = getGoogleLoginLink();

  return (
    <div>
      <Link href={link}>구글 로그인</Link>
    </div>
  );
}
