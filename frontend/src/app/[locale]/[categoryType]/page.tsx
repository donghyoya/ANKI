import React from 'react';
import { AuthService } from '@/services/AuthService';
import { CookieService } from '@/services/CookieService';
import CategoryTypeClientPage from './clientPage';

export default async function CategoryTypePage() {
  const authService = new AuthService(new CookieService());
  const isLoggedIn = await authService.isLoggedIn();

  return <CategoryTypeClientPage isLoggedIn={isLoggedIn} />;
}
