import Image from 'next/image';
import { getGoogleLoginLink } from '@/api/auth';
import Link from 'next/link';

import styles from './login.module.scss';

export default function LoginPage() {
  return (
    <div className={styles['page']}>
      <div className={styles['container']}>
        <Image src="/logo.svg" width={267} height={223} alt="logo" />
        <Link href={getGoogleLoginLink()} className={styles['login-button']}>
          <Image src="/login.svg" width={173} height={40} alt="google login" />
        </Link>
      </div>
    </div>
  );
}
