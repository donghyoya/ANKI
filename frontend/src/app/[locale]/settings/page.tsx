'use client';

import React, { FormEvent, useEffect, useState } from 'react';
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
import { LANGUAGE_OPTIONS } from '@/utils/dummyData';
import { mockGetUserOption, mockPostUserOption } from '@/api/mock';
import { UserOption } from '@/types/schemes';
import { MdOutlinedTextField } from '@material/web/textfield/outlined-text-field';

export default function SettingsPage() {
  const locale = useLocale();
  const router = useRouter();
  const t = useTranslations();

  const { width } = useWindowSize();
  const isCompact = width < 1200;

  const [selectedLocale, setSelectedLocale] = useState(locale);

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

  const [userOptions, setUserOptions] = useState<UserOption | null>(null);

  const handleOnChangeTextField = (e: FormEvent<MdOutlinedTextField>) => {
    const value = Number((e.target as MdOutlinedTextField).value);
    const id = (e.target as MdOutlinedTextField).id;
    if (userOptions === null) return;
    setUserOptions({
      ...userOptions,
      [id]: value
    });
  };

  useEffect(() => {
    const fetchOptions = async () => {
      const options = await mockGetUserOption();
      console.log(options);
      setUserOptions(options as UserOption);
    };
    fetchOptions();

    const postOptions = async () => {
      if (userOptions === null) return;
      await mockPostUserOption(userOptions);
    };
    postOptions();
    return () => {
      postOptions();
    };
  }, [locale, userOptions]);

  useEffect(() => {
    if (userOptions === null) return;
    console.log(userOptions);
  }, [userOptions]);

  const webView = (
    <div className={styles.page}>
      <div className={styles.contents}>
        <h1 className={styles.title}>{t('settings.settings')}</h1>
        <div className={styles['group']}>
          <h3 className={styles['group-title']}>{t('settings.system')}</h3>
          <div className={classNames(styles['field-section'], styles['first-field-section'])}>
            <label className={styles.label}>{t('settings.language')}</label>
            <OutlinedSelect value={locale}>
              {LANGUAGE_OPTIONS.map((lang) => (
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
            <label className={styles.label}>{t('settingsPage.reviewCount')}</label>
            <OutlinedTextField
              id="todayReviewWords"
              value={userOptions?.todayReviewWords.toString()}
              onChange={handleOnChangeTextField}
            ></OutlinedTextField>
          </div>
          <div className={styles['field-section']}>
            <label className={styles.label}>{t('settingsPage.newCount')}</label>
            <OutlinedTextField
              id="todayStudyWords"
              value={userOptions?.todayStudyWords.toString()}
              onChange={handleOnChangeTextField}
            ></OutlinedTextField>
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
            <div slot="headline">{t('settingsPage.language')}</div>
            <div slot="supporting-text">
              {LANGUAGE_OPTIONS.find((lang) => lang.code === locale)?.label || 'English'}
            </div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu id="language-menu" anchor="language-anchor" anchorCorner="end-end" xOffset={-160}>
            {LANGUAGE_OPTIONS.map((lang) => (
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
            <div slot="headline">{t('settingsPage.theme')}</div>
            <div slot="supporting-text">classic</div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu id="theme-menu" anchor="theme-anchor" anchorCorner="end-end" xOffset={-112}>
            <MenuItem>classic</MenuItem>
          </Menu>
        </div>
        <ListItem type="button">
          <div slot="headline">{t('settingsPage.reviewCount')}</div>
          <div slot="trailing-supporting-text">20</div>
        </ListItem>
        <ListItem type="button">
          <div slot="headline">{t('settingsPage.newCount')}</div>
          <div slot="trailing-supporting-text">20</div>
        </ListItem>
        <ListItem type="button">{t('settingsPage.signOut')}</ListItem>
        <ListItem type="button">{t('settingsPage.deleteAccount')}</ListItem>
      </List>
    </div>
  );

  const pageView = isCompact ? mobileView : webView;

  return pageView;
}
