'use client';

import React, { useState } from 'react';
import { useLocale } from 'next-intl';
import { useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';

import styles from './Settings.module.scss';
import { OutlinedTextField } from '@/components/material-components/TextField';
import { Icon } from '@/components/material-components/IconButton/IconButton';
import { OutlinedSelect, SelectOption } from '@/components/material-components/Select';
import TextButton from '@/components/material-components/TextButton';
import { List, ListItem } from '@/components/material-components/List';
import { Menu, MenuItem } from '@/components/material-components/Menu';

import classNames from 'classnames';
import { useWindowSize } from '@/hooks/useWindowSize';

export default function SettingsPage() {
  const locale = useLocale();
  const router = useRouter();
  const t = useTranslations();

  const { width } = useWindowSize();
  const isCompact = width < 1200;

  const [selectedLocale, setSelectedLocale] = useState(locale);

  const languageOptions = [
    { code: 'ar', label: 'العربية' }, // 아랍어
    { code: 'en', label: 'English' }, // 영어
    { code: 'es', label: 'Español' }, // 스페인어
    { code: 'fr', label: 'Français' }, // 프랑스어
    { code: 'id', label: 'Bahasa Indonesia' }, // 인도네시아어
    { code: 'ja', label: '日本語' }, // 일본어
    { code: 'ko', label: '한국어' }, // 한국어
    { code: 'mn', label: 'Монгол' }, // 몽골어
    { code: 'ru', label: 'Русский' }, // 러시아어
    { code: 'th', label: 'ไทย' }, // 태국어
    { code: 'vi', label: 'Tiếng Việt' }, // 베트남어
    { code: 'zh', label: '中文' } // 중국어
  ];

  const handleChangeLanguage = (newLocale: string) => {
    setSelectedLocale(newLocale);
    router.push(`/${newLocale}/settings`);
    router.refresh();
  };

  const handleLanguageMenuClick = () => {
    const menu = document.getElementById('language-menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const handleThemeMenuClick = () => {
    const menu = document.getElementById('theme-menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const webView = (
    <div className={styles.page}>
      <div className={styles.contents}>
        <h1 className={styles.title}>{t('settings.settings')}</h1>
        <div className={styles['group']}>
          <h3 className={styles['group-title']}>{t('settings.system')}</h3>
          <div className={classNames(styles['field-section'], styles['first-field-section'])}>
            <label className={styles.label}>{t('settings.language')}</label>
            <OutlinedSelect value={locale}>
              {languageOptions.map((lang) => (
                <SelectOption
                  key={lang.code}
                  value={lang.code}
                  selected={lang.code === locale}
                  onClick={() => handleChangeLanguage(lang.code)}
                >
                  {lang.label}
                </SelectOption>
              ))}
            </OutlinedSelect>
          </div>
          <div className={styles['field-section']}>
            <label className={styles.label}>{t('settings.theme')}</label>
            <OutlinedSelect>
              <SelectOption value="classic" selected>
                <div>Classic</div>
              </SelectOption>
            </OutlinedSelect>
          </div>
        </div>
        <div className={styles['group']}>
          <h3 className={styles['group-title']}>{t('settings.learning')}</h3>
          <div className={classNames(styles['field-section'], styles['first-field-section'])}>
            <label className={styles.label}>{t('settings.reviewCount')}</label>
            <OutlinedTextField value="20"></OutlinedTextField>
          </div>
          <div className={styles['field-section']}>
            <label className={styles.label}>{t('settings.newCount')}</label>
            <OutlinedTextField value="20"></OutlinedTextField>
          </div>
        </div>
        <div className={styles['group']}>
          <h3 className={styles['group-title']}>{t('settings.account')}</h3>
          <div className={styles['sign-out-button']}>
            <TextButton>{t('settings.signOut')}</TextButton>
          </div>
          <TextButton>{t('settings.deleteAccount')}</TextButton>
        </div>
      </div>
    </div>
  );

  const mobileView = (
    <div className={styles.page}>
      <List className={styles.list}>
        <div style={{ position: 'relative' }}>
          <ListItem type="button" id="language-anchor" onClick={handleLanguageMenuClick}>
            <div slot="headline">{t('settings.language')}</div>
            <div slot="supporting-text">
              {languageOptions.find((lang) => lang.code === locale)?.label || 'English'}
            </div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu id="language-menu" anchor="language-anchor" anchorCorner="end-end" xOffset={-160}>
            {languageOptions.map((lang) => (
              <MenuItem
                key={lang.code}
                selected={lang.code === selectedLocale}
                onClick={() => {
                  handleChangeLanguage(lang.code);
                }}
              >
                {lang.label}
              </MenuItem>
            ))}
          </Menu>
        </div>
        <div style={{ position: 'relative' }}>
          <ListItem type="button" id="theme-anchor" onClick={handleThemeMenuClick}>
            <div slot="headline">{t('settings.theme')}</div>
            <div slot="supporting-text">classic</div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu id="theme-menu" anchor="theme-anchor" anchorCorner="end-end" xOffset={-112}>
            <MenuItem>classic</MenuItem>
          </Menu>
        </div>
        <ListItem type="button">
          <div slot="headline">{t('settings.reviewCount')}</div>
          <div slot="trailing-supporting-text">20</div>
        </ListItem>
        <ListItem type="button">
          <div slot="headline">{t('settings.newCount')}</div>
          <div slot="trailing-supporting-text">20</div>
        </ListItem>
        <ListItem type="button">{t('settings.signOut')}</ListItem>
        <ListItem type="button">{t('settings.deleteAccount')}</ListItem>
      </List>
    </div>
  );

  const pageView = isCompact ? mobileView : webView;

  return pageView;
}
