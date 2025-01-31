'use client'; // 클라이언트 컴포넌트로 설정

import React from "react";
import styles from "./DeckCard.module.scss"
import FilledButton from "@/components/material-components/FilledButton";
import TextButton from "@/components/material-components/TextButton";
import { Icon } from "@/components/material-components/IconButton/IconButton";

// Props 타입 정의
interface DeckCardProps {
  title: string;
  isCompleted: boolean;
  wordCount: number;
  locale: string;
  buttonLabels: {
    viewWords: string;
    learn: string;
  };
  onViewWords: () => void;
  onLearn: () => void;
}

// DeckCard 컴포넌트
const DeckCard = ({ title, isCompleted, wordCount, locale, buttonLabels, onViewWords, onLearn }: DeckCardProps) => {
  return (
    <div className={styles.card}>
      <div className={styles.info}>
        <div className={styles['title-container']}>
          <h2 className={`${styles.title} md-typescale-title-large`}>{title}</h2>
          {isCompleted && <Icon className={styles['check-icon']}>check_circle</Icon>}
        </div>
        <span className={`${styles['word-count']} md-typescale-label-large`}>{wordCount.toLocaleString(locale)} words</span>
      </div>
      <div className={styles['bottom-contents']}>
        <div className={styles['button-container']}>
          <TextButton onClick={onViewWords}>{buttonLabels.viewWords}</TextButton>
          <FilledButton onClick={onLearn}>{buttonLabels.learn}</FilledButton>
        </div>
        <div className={styles['progress-bar']}>
        </div>
      </div>
    </div>
  );
};

export default DeckCard;