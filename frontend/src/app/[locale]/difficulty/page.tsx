import React from 'react';
import { AuthService } from '@/services/AuthService';
import { CookieService } from '@/services/CookieService';
import DifficultyClientPage from './clientPage';

export default async function DifficultyPage() {
  const authService = new AuthService(new CookieService());
  const isLoggedIn = await authService.isLoggedIn();

  return <DifficultyClientPage isLoggedIn={isLoggedIn} />;
}
