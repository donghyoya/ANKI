'use client';

import React, { FormEvent, useEffect, useState } from 'react';
import { useLocale } from 'next-intl';
import { useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';
import { useSelector } from 'react-redux';

import { OutlinedTextField } from '@/components/material-components/TextField';
import { Icon } from '@/components/material-components/IconButton/IconButton';
import { OutlinedSelect, SelectOption } from '@/components/material-components/Select';
import TextButton from '@/components/material-components/TextButton';
import { List, ListItem } from '@/components/material-components/List';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import FilledButton from '@/components/material-components/FilledButton';
import { MdOutlinedTextField } from '@material/web/textfield/outlined-text-field';

import { useWindowSize } from '@/hooks/useWindowSize';
import { getUserOption, postUserOption } from '@/api/option';

import { UserOption } from '@/types/schemes';
import { Locale } from '@/types/Locale';
import { LANGUAGE_OPTIONS, UTC_OFFSET_OPTIONS } from '@/utils/dummyData';
import { RootState } from '@/store';

import classNames from 'classnames';
import styles from './SettingsPage.module.scss';

export default function SettingsPage() {
  const locale = useLocale();
  const router = useRouter();
  const t = useTranslations();

  const { width } = useWindowSize();
  const isCompact = width < 1200;

  const [userOptions, setUserOptions] = useState<UserOption | null>(null);
  const { accessToken } = useSelector((state: RootState) => state.auth);

  const handleChangeLanguage = (newLocale: Locale) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, languageCode: newLocale });
  };

  const handleLanguageMenuClick = (e: FormEvent<MdOutlinedTextField>) => {
    const menu = document.getElementById('language-menu') as HTMLDialogElement;
    console.log(e);
    menu.open = !menu.open;
  };

  const handleThemeMenuClick = () => {
    const menu = document.getElementById('theme-menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const handleUtcOffsetMenuClick = () => {
    const menu = document.getElementById('utc-offset-menu') as HTMLDialogElement;
    menu.open = !menu.open;
  };

  const handleOnChangeTextField = (e: FormEvent<MdOutlinedTextField>) => {
    // const value = Number((e.target as MdOutlinedTextField).value);
    // if (userOptions === null) return;
    // setUserOptions({
    //   ...userOptions,
    // });
  };

  const handleChangeUtcOffset = (newUtcOffset: number) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, utcOffset: newUtcOffset });
  };

  const handleSave = async () => {
    if (userOptions === null || accessToken === null) return;
    try {
      await postUserOption(userOptions, accessToken);
      // TODO: replace alert with modal
      alert('Saved');
    } catch {
      alert('Failed to save');
    }

    if (locale !== userOptions.languageCode) {
      router.push(`/${userOptions.languageCode}/settings`);
      router.refresh();
    }
  };

  useEffect(() => {
    if (!accessToken) return;
    const fetchOptions = async () => {
      const options = await getUserOption(accessToken);
      console.log(options);
      setUserOptions(options as UserOption);
    };
    fetchOptions();
  }, [accessToken]);

  useEffect(() => {
    if (!userOptions) return;
    console.log('userOptions', userOptions);
  }, [userOptions]);

  const webView = (
    <div className={styles.page}>
      <div className={styles.contents}>
        <FilledButton onClick={handleSave}>Save</FilledButton>
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
                  onClick={() => handleChangeLanguage(lang.code as Locale)}
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
            <OutlinedTextField
              id="dailyReviewWords"
              value={userOptions?.dailyReviewWords?.toString()}
              onChange={handleOnChangeTextField}
            ></OutlinedTextField>
          </div>
          <div className={styles['field-section']}>
            <label className={styles.label}>{t('settings.newCount')}</label>
            <OutlinedTextField
              id="dailyStudyWords"
              value={userOptions?.dailyStudyWords?.toString()}
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
      <FilledButton onClick={handleSave}>Save</FilledButton>
      <List className={styles.list}>
        <div style={{ position: 'relative' }}>
          <ListItem type="button" id="language-anchor" onClick={handleLanguageMenuClick}>
            <div slot="headline">{t('settings.language')}</div>
            <div slot="supporting-text">
              {LANGUAGE_OPTIONS.find((lang) => lang.code === userOptions?.languageCode)?.label ||
                'English'}
            </div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu id="language-menu" anchor="language-anchor" anchorCorner="end-end" xOffset={-160}>
            {LANGUAGE_OPTIONS.map((lang) => (
              <MenuItem
                key={lang.code}
                selected={lang.code === userOptions?.languageCode}
                onClick={() => {
                  handleChangeLanguage(lang.code as Locale);
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
        <div style={{ position: 'relative' }}>
          <ListItem type="button" id="utc-offset-anchor" onClick={handleUtcOffsetMenuClick}>
            <div slot="headline">{t('settings.utcOffset')}</div>
            <div slot="supporting-text">
              {UTC_OFFSET_OPTIONS.find((offset) => offset.code === userOptions?.utcOffset)?.label ||
                'UTC+00:00'}
            </div>
            <Icon slot="end">arrow_drop_down</Icon>
          </ListItem>
          <Menu
            id="utc-offset-menu"
            anchor="utc-offset-anchor"
            anchorCorner="end-end"
            xOffset={-160}
          >
            {UTC_OFFSET_OPTIONS.map((offset) => (
              <MenuItem
                key={offset.code}
                selected={offset.code === userOptions?.utcOffset}
                onClick={() => {
                  handleChangeUtcOffset(offset.code);
                }}
              >
                {offset.label}
              </MenuItem>
            ))}
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
