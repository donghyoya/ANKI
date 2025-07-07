'use client';

import React, { useEffect, useState } from 'react';
import { useLocale } from 'next-intl';
import { useRouter } from 'next/navigation';
import { useTranslations } from 'next-intl';

import { OutlinedTextField } from '@/components/material-components/TextField';
import { Icon } from '@/components/material-components/IconButton/IconButton';
import { OutlinedSelect, SelectOption } from '@/components/material-components/Select';
import TextButton from '@/components/material-components/TextButton';
import { List, ListItem } from '@/components/material-components/List';
import { Menu, MenuItem } from '@/components/material-components/Menu';
import FilledButton from '@/components/material-components/FilledButton';

import { useWindowSize } from '@/hooks/useWindowSize';
import { getUserOption, postUserOption } from '@/api/option';

import { UserOption } from '@/types/schemes';
import { Locale } from '@/types/Locale';
import { LANGUAGE_OPTIONS, UTC_OFFSET_OPTIONS, THEME_OPTIONS } from '@/utils/dummyData';

import classNames from 'classnames';
import styles from './SettingsPage.module.scss';
import { useErrorBoundary } from 'react-error-boundary';
import { Theme, useTheme } from '@/context/ThemeContext';
import CustomDialog from '@/components/Dialogs/CustomDialog';
import { useSnackbar } from '@/components/Snackbar/SnackbarProvider';

export default function SettingsPage() {
  const locale = useLocale();
  const router = useRouter();
  const t = useTranslations();

  const { width } = useWindowSize();
  const isCompact = width < 600;
  const isMobile = width < 1200;

  const { showBoundary } = useErrorBoundary();
  const { showSnackbar } = useSnackbar();
  const { theme, setTheme } = useTheme();

  const [userOptions, setUserOptions] = useState<UserOption | null>(null);
  const [isReviewCountDialogOpen, setIsReviewCountDialogOpen] = useState(false);
  const [isNewCountDialogOpen, setIsNewCountDialogOpen] = useState(false);

  // userOptions 기본값
  const DEFAULT_USER_OPTIONS: Required<UserOption> = {
    languageCode: 'en',
    utcOffset: 0,
    dailyReviewWords: 20,
    dailyStudyWords: 20
  };

  const languageCode = userOptions?.languageCode ?? DEFAULT_USER_OPTIONS.languageCode;
  const offsetCode = userOptions?.utcOffset ?? DEFAULT_USER_OPTIONS.utcOffset;
  let reviewCount = (
    userOptions?.dailyReviewWords ?? DEFAULT_USER_OPTIONS.dailyReviewWords
  ).toString();
  let newCount = (userOptions?.dailyStudyWords ?? DEFAULT_USER_OPTIONS.dailyStudyWords).toString();

  const language = LANGUAGE_OPTIONS.find((lang) => lang.code === languageCode)?.label || 'English';
  const utcOffset =
    UTC_OFFSET_OPTIONS.find((offset) => offset.code === offsetCode)?.label || 'UTC+00:00';

  // 비어있는 userOptions 속성에 기본값 채우기
  const applyDefaults = (options: Partial<UserOption>): Required<UserOption> => {
    return {
      languageCode: options.languageCode ?? DEFAULT_USER_OPTIONS.languageCode,
      utcOffset: options.utcOffset ?? DEFAULT_USER_OPTIONS.utcOffset,
      dailyReviewWords: options.dailyReviewWords ?? DEFAULT_USER_OPTIONS.dailyReviewWords,
      dailyStudyWords: options.dailyStudyWords ?? DEFAULT_USER_OPTIONS.dailyStudyWords
    };
  };

  // Change value functions
  const handleChangeLanguage = (newLocale: Locale) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, languageCode: newLocale });
  };

  const handleChangeTheme = (newTheme: Theme) => {
    setTheme(newTheme);
  };

  const handleChangeUtcOffset = (newUtcOffset: number) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, utcOffset: newUtcOffset });
  };

  const handleChangeReviewCount = (newReviewCount: number) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, dailyReviewWords: newReviewCount });
  };

  const handleChangeNewCount = (newStudyCount: number) => {
    if (userOptions === null) return;
    setUserOptions({ ...userOptions, dailyStudyWords: newStudyCount });
  };

  // Menus Click Functions in Mobile view
  const handleLanguageMenuClick = () => {
    const menu = document.getElementById('language-menu') as HTMLDialogElement;
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

  // Dialog handlers in Mobile view
  const handleReviewCountDialog = (isOpen: boolean) => setIsReviewCountDialogOpen(isOpen);

  const handleNewCountDialog = (isOpen: boolean) => setIsNewCountDialogOpen(isOpen);

  // Save function
  const handleSave = async () => {
    if (userOptions === null) {
      showSnackbar(t('failedToSave'));
      return;
    }

    const defaultOptions = applyDefaults(userOptions);

    try {
      await postUserOption(defaultOptions);
      showSnackbar(t('saved'));
    } catch {
      showSnackbar(t('failedToSave'));
    }

    if (locale !== defaultOptions.languageCode) {
      router.push(`/${defaultOptions.languageCode}/settings`);
      router.refresh();
    }
  };

  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const response = await getUserOption();
        console.log(response);
        setUserOptions(response as UserOption);
      } catch (error) {
        showBoundary(error);
      }
    };
    fetchOptions();
  }, [showBoundary]);

  useEffect(() => {
    if (!userOptions) return;
    console.log('userOptions', userOptions);
  }, [userOptions]);

  const webView = (
    <>
      {/* [System] */}
      <div className={styles['group']}>
        <h3 className={styles['group-title']}>{t('settings.system')}</h3>
        {/* Language */}
        <div className={styles['field-section']}>
          <label className={styles.label}>{t('settings.language')}</label>
          <OutlinedSelect value={languageCode}>
            {LANGUAGE_OPTIONS.map((lang) => (
              <SelectOption
                key={lang.code}
                value={lang.code}
                onClick={() => handleChangeLanguage(lang.code as Locale)}
              >
                {lang.label}
              </SelectOption>
            ))}
          </OutlinedSelect>
        </div>
        {/* Theme */}
        <div className={styles['field-section']}>
          <label className={styles.label}>{t('settings.theme')}</label>
          <OutlinedSelect value={theme}>
            {THEME_OPTIONS.map((theme) => (
              <SelectOption
                key={theme.value}
                value={theme.value}
                onClick={() => handleChangeTheme(theme.value as Theme)}
              >
                {t(theme.messageKey)}
              </SelectOption>
            ))}
          </OutlinedSelect>
        </div>
        {/* Time zone */}
        <div className={classNames(styles['field-section'], styles['last-field-section'])}>
          <label className={styles.label}>{t('settings.utcOffset')}</label>
          <OutlinedSelect value={offsetCode?.toString()}>
            {UTC_OFFSET_OPTIONS.map((offset) => (
              <SelectOption
                key={offset.code}
                value={offset.code?.toString()}
                onClick={() => handleChangeUtcOffset(offset.code)}
              >
                {offset.label}
              </SelectOption>
            ))}
          </OutlinedSelect>
        </div>
      </div>
      {/* [Learning] */}
      <div className={styles['group']}>
        <h3 className={styles['group-title']}>{t('settings.learning')}</h3>
        {/* Daily review word count */}
        <div className={styles['field-section']}>
          <label className={styles.label}>{t('settings.reviewCount')}</label>
          <OutlinedTextField
            id="dailyReviewWords"
            value={reviewCount}
            onChange={(e) => {
              reviewCount = (e.target as HTMLInputElement).value;
              handleChangeReviewCount(Number(reviewCount));
            }}
          />
        </div>
        {/* Daily new word count */}
        <div className={classNames(styles['field-section'], styles['last-field-section'])}>
          <label className={styles.label}>{t('settings.newCount')}</label>
          <OutlinedTextField
            id="dailyStudyWords"
            value={newCount}
            onChange={(e) => {
              newCount = (e.target as HTMLInputElement).value;
              handleChangeNewCount(Number(newCount));
            }}
          />
        </div>
      </div>
    </>
  );

  const mobileView = (
    <>
      {/* [System] */}
      <div className={styles.group}>
        <h3 className={styles['group-title']}>{t('settings.system')}</h3>
        <List className={styles.list}>
          {/* Language */}
          <div style={{ position: 'relative' }}>
            <ListItem type="button" id="language-anchor" onClick={handleLanguageMenuClick}>
              <div slot="headline">{t('settings.language')}</div>
              <div slot="supporting-text">{language}</div>
              <Icon slot="end">arrow_drop_down</Icon>
            </ListItem>
            <Menu
              id="language-menu"
              anchor="language-anchor"
              anchorCorner="end-end"
              xOffset={-160}
              className={styles['language-menu']}
            >
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
          {/* Theme */}
          <div style={{ position: 'relative' }}>
            <ListItem type="button" id="theme-anchor" onClick={handleThemeMenuClick}>
              <div slot="headline">{t('settings.theme')}</div>
              <div slot="supporting-text">{t(`settings.${theme}`)}</div>
              <Icon slot="end">arrow_drop_down</Icon>
            </ListItem>
            <Menu
              id="theme-menu"
              anchor="theme-anchor"
              anchorCorner="end-end"
              xOffset={-201}
              className={styles['theme-menu']}
            >
              {THEME_OPTIONS.map((theme) => (
                <MenuItem key={theme.value} onClick={() => handleChangeTheme(theme.value as Theme)}>
                  {t(theme.messageKey)}
                </MenuItem>
              ))}
            </Menu>
          </div>
          {/* Time zone */}
          <div style={{ position: 'relative' }}>
            <ListItem type="button" id="utc-offset-anchor" onClick={handleUtcOffsetMenuClick}>
              <div slot="headline">{t('settings.utcOffset')}</div>
              <div slot="supporting-text">{utcOffset}</div>
              <Icon slot="end">arrow_drop_down</Icon>
            </ListItem>
            <Menu
              id="utc-offset-menu"
              anchor="utc-offset-anchor"
              anchorCorner="end-end"
              xOffset={-113}
              className={styles['time-zone-menu']}
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
        </List>
      </div>
      {/* [Learning] */}
      <div className={styles.group}>
        <h3 className={styles['group-title']}>{t('settings.learning')}</h3>
        <List className={styles.list}>
          {/* Daily review word count */}
          <ListItem type="button" onClick={() => handleReviewCountDialog(true)}>
            <div slot="headline">{t('settings.reviewCount')}</div>
            <div slot="trailing-supporting-text" className={styles['learning-count']}>
              {reviewCount}
            </div>
          </ListItem>
          <CustomDialog
            open={isReviewCountDialogOpen}
            headline={t('settings.reviewCount')}
            prompt={
              <OutlinedTextField
                id="dailyReviewWords"
                value={reviewCount}
                onChange={(e) => (reviewCount = (e.target as HTMLInputElement).value)}
              />
            }
            firstButtonString={t('cancel')}
            secondButtonString={t('ok')}
            firstButtonOnclick={() => handleReviewCountDialog(false)}
            secondButtonOnclick={() => {
              handleChangeReviewCount(Number(reviewCount));
              handleReviewCountDialog(false);
            }}
          />
          {/* Daily new word count */}
          <ListItem type="button" onClick={() => handleNewCountDialog(true)}>
            <div slot="headline">{t('settings.newCount')}</div>
            <div slot="trailing-supporting-text" className={styles['learning-count']}>
              {newCount}
            </div>
          </ListItem>
          <CustomDialog
            open={isNewCountDialogOpen}
            headline={t('settings.newCount')}
            prompt={
              <OutlinedTextField
                id="dailyStudyWords"
                value={newCount}
                onChange={(e) => (newCount = (e.target as HTMLInputElement).value)}
              />
            }
            firstButtonString={t('cancel')}
            secondButtonString={t('ok')}
            firstButtonOnclick={() => handleNewCountDialog(false)}
            secondButtonOnclick={() => {
              handleChangeNewCount(Number(newCount));
              handleNewCountDialog(false);
            }}
          />
        </List>
      </div>
    </>
  );

  const pageView = (
    <div className={styles.page}>
      <div className={styles.contents}>
        {!isCompact && (
          <div className={styles.header}>
            <h1 className={styles.title}>{t('settings.settings')}</h1>
            <FilledButton onClick={handleSave}>{t('save')}</FilledButton>
          </div>
        )}
        {isMobile ? mobileView : webView}
        {/* [Account] */}
        <div className={styles.group}>
          <h3 className={styles['group-title']}>{t('settings.account')}</h3>
          {/* Sign out */}
          <div className={styles['sign-out-button']}>
            <TextButton>{t('settings.signOut')}</TextButton>
          </div>
          {/* Delete account */}
          <TextButton>{t('settings.deleteAccount')}</TextButton>
        </div>
        {isCompact && (
          <div className={styles['button-container-compact']}>
            <FilledButton className={styles['save-button-compact']} onClick={handleSave}>
              {t('save')}
            </FilledButton>
          </div>
        )}
      </div>
    </div>
  );

  return pageView;
}
