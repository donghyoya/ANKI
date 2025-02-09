'use client'; // 클라이언트 컴포넌트로 설정

import React from "react";
import styles from "./DeckCard.module.scss"
import { DeckCardProps } from "@/components/DeckCard/types";
import FilledButton from "@/components/material-components/FilledButton";
import TextButton from "@/components/material-components/TextButton";
import { Icon } from "@/components/material-components/IconButton/IconButton";
import { OutlinedCard } from "@/components/Card/Card";
import ProgressBar from "@/components/ProgressBar/ProgressBar";
import {bars} from "./bars";

// DeckCard 컴포넌트
const DeckCard = ({ title, isCompleted, wordCount, locale, buttonLabels, onViewWords, onLearn }: DeckCardProps) => {
  return (
    <OutlinedCard ripple={false}>
      <div className={styles.card}>
        <div className={styles.info}>
          <div className={styles['title-container']}>
            <h2 className={styles.title}>{title}</h2>
            {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
          </div>
          <span className={styles['word-count']}>{wordCount.toLocaleString(locale)} words</span>
        </div>
        <div className={styles['bottom-contents']}>
          <div className={styles['button-container']}>
            <TextButton onClick={onViewWords}>{buttonLabels.viewWords}</TextButton>
            <FilledButton onClick={onLearn}>{buttonLabels.learn}</FilledButton>
          </div>
          <ProgressBar bars={bars} height={12}></ProgressBar>
        </div>
      </div>
    </OutlinedCard>
  );
};

export default DeckCard;