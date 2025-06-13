import { useEffect, useState } from 'react';

import { KoreanCardDetail, UserCard } from '@/types/schemes';
import { Category } from '@/types/Category';
import { Rating, State } from 'ts-fsrs';

import { getLearningCards } from '@/api/study';
import { DUMMY_RATING_PREVIEW } from '@/utils/dummyData';
import { getKoreanCardDetail } from '@/api/cards';

export const useStudyQueue = (category: Category) => {
  const iPreview = DUMMY_RATING_PREVIEW;

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [currentCardDetail, setCurrentCardDetail] = useState<KoreanCardDetail | null>(null);
  const [error, setError] = useState<Error | null>(null);
  const [isCompleted, setIsCompleted] = useState(false);

  const repeat = (rating: Rating) => {
    if (studyQueue === null || currentCard === null) return;
    const newStudyQueue = [
      ...studyQueue.filter((card) => card.koreanCard.cardId !== currentCard.koreanCard.cardId),
      {
        ...currentCard,
        studyInfo: {
          ...currentCard.studyInfo,
          state: rating === Rating.Again ? State.Learning : State.Review
        }
      }
    ] as UserCard[];
    setStudyQueue(newStudyQueue);
    setCurrentCard(
      newStudyQueue.filter((card) => card.studyInfo.state !== State.Review)[0] ?? null
    );
  };

  // 학습 큐 요청하기
  useEffect(() => {
    const fetchCards = async () => {
      try {
        const response = await getLearningCards('new', category);
        console.log('fetchCards', response);
        if (response && 'content' in response) {
          setStudyQueue(response.content);
          setCurrentCard(
            response.content.filter((card) => card.studyInfo.state !== State.Review)[0] ?? null
          );
        }
      } catch (error) {
        setError(error as Error);
      }
    };
    if (studyQueue === null) {
      fetchCards();
    }
  }, [studyQueue, category]);

  // 현재 카드 설정하기
  useEffect(() => {
    if (studyQueue) {
      console.log('studyQueue', studyQueue);
      let newCard;
      // 학습 큐에 card.due < new Date()인 카드가 있으면 그 카드를 현재 카드로 설정
      newCard = studyQueue.find((c) => new Date(c.studyInfo.due) < new Date());
      if (!newCard) {
        // state가 learned가 아닌 카드 중에서 due가 가장 작은 카드
        newCard = studyQueue.reduce((minCard, card) => {
          return new Date(card.studyInfo.due) < new Date(minCard.studyInfo.due) ? card : minCard;
        });
      }
      if (newCard) setCurrentCard(newCard);
      else setIsCompleted(true);
    }
  }, [studyQueue]);

  useEffect(() => {
    console.log('currentCard:', currentCard);

    const fetchCardDetail = async () => {
      if (currentCard) {
        const cardDetail = await getKoreanCardDetail(currentCard.koreanCard.cardId);
        console.log('cardDetail', cardDetail);
        setCurrentCardDetail(cardDetail);
      }
    };

    fetchCardDetail();
  }, [currentCard]);

  return {
    currentCard,
    currentCardDetail,
    studyQueue,
    iPreview,
    repeat,
    error,
    isCompleted
  };
};
