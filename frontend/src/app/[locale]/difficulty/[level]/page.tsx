'use client';

import React from "react";
import { useParams, notFound } from "next/navigation";
import { useTranslations } from "next-intl";
import FilledButton from "@/components/material-components/FilledButton";
import { Icon, IconButton } from "@/components/material-components/IconButton/IconButton";
import WordList from "@/components/WordList/WordList";
import styles from "./Level.module.scss";

const difficulty = ["beginner", "intermediate", "advanced"];

export default function DifficultyWordsPage() {
    const t = useTranslations();
    const { level } = useParams() ?? {};

    if (!difficulty.includes(level as string)) {
        notFound();
    }

    return (
        <div className={styles.page}>
            <div className={styles.content}>
                <div className={styles["header-container"]}>
                    <h1 className={styles.title}>{t(level)}</h1>
                    <div className={styles["button-container"]}>
                        <FilledButton className={styles["learn-button"]}>Learn</FilledButton>
                        <IconButton>
                            <Icon>more_vert</Icon>
                        </IconButton>
                    </div>
                </div>
                <div className={styles["list-container"]}>
                    <WordList KoreanWord="안녕" ForeignWord="hi" />
                    <WordList KoreanWord="안녕" ForeignWord="hi" />
                </div>
            </div>
        </div>
    );
}
