'use client';

import React from "react";
import { OutlinedCard } from "../Card/Card";
import styles from './WordList.module.scss';

interface WordListProps {
    KoreanWord: string;
    ForeignWord: string;
}

const WordList = ({ KoreanWord, ForeignWord }: WordListProps) => {
    return (
        <div>
            <OutlinedCard className={styles.card}>
                <div className={styles.content}>
                    <h3 className={styles['korean-word']}>{KoreanWord}</h3>
                    <div className={styles['right-container']}>
                        <div className={styles.line}></div>
                        <h3 className={styles['foreign-word']}>{ForeignWord}</h3>
                    </div>
                </div>
            </OutlinedCard>
        </div>
    );
}

export default WordList;