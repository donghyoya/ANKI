import { useEffect, useState, useMemo } from 'react';
import { useToken } from '@/hooks/useToken';

import { getUserCards, postCardStudyInfo } from '@/api/study';

import { UserCard } from '@/types/schemes';
import { Category } from '@/types/Category';
import { IPreview, Rating } from 'ts-fsrs';
import { createFSRS } from '@/utils/FSRS';
import { FSRSCard } from '@/types/FSRS';

export const useStudyQueue = (category: Category) => {
  const { token } = useToken();

  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [isCompleted, setIsCompleted] = useState(false);
  const [error, setError] = useState<Error | null>(null);

  const f = createFSRS();

  const repeat = async (rating: Rating) => {
    // 에러 처리
    if (!token) {
      setError(new Error('UNAUTHORIZED'));
      return;
    }
    if (!currentCard) {
      setError(new Error('CURRENT_CARD_NOT_FOUND'));
      return;
    }
    if (!studyQueue) {
      setError(new Error('STUDY_QUEUE_NOT_FOUND'));
      return;
    }

    // 새로운 카드 상태 계산
    const newIPreview = f.repeat(currentCard.fsrsParameters, new Date());
    const newRecordLogItem = newIPreview[rating as keyof IPreview];

    const newFsrsParameters: FSRSCard =
      typeof newRecordLogItem === 'function'
        ? newRecordLogItem().next().value.card
        : newRecordLogItem.card;

    // 서버에 카드 상태 업데이트
    try {
      await postCardStudyInfo(currentCard.cardId, newFsrsParameters, token);
    } catch (error) {
      setError(error as Error);
      return;
    }

    // 로컬 상태 업데이트
    const newCard = {
      ...currentCard,
      fsrsParameters: newFsrsParameters
    } as UserCard;
    setStudyQueue(studyQueue?.map((c) => (c.cardId === currentCard.cardId ? newCard : c)));
  };

  // 학습 큐 요청하기
  useEffect(() => {
    async function fetchStudyQueue() {
      if (!token) return;
      try {
        const newCards = await getUserCards('new', category, token);
        // const reviewCards = await getUserCards('review', category, token);
        setStudyQueue([...newCards.content]);
      } catch (error) {
        setError(error as Error);
      }
    }
    fetchStudyQueue();
  }, [category, token]);

  // 현재 카드 설정하기
  useEffect(() => {
    if (studyQueue) {
      console.log('studyQueue', studyQueue);
      let newCard;
      // 학습 큐에 card.due < new Date()인 카드가 있으면 그 카드를 현재 카드로 설정
      newCard = studyQueue.find((c) => new Date(c.fsrsParameters.due) < new Date());
      if (!newCard) {
        // state가 learned가 아닌 카드 중에서 due가 가장 작은 카드
        newCard = studyQueue.reduce((minCard, card) => {
          return new Date(card.fsrsParameters.due) < new Date(minCard.fsrsParameters.due)
            ? card
            : minCard;
        });
      }
      if (newCard) setCurrentCard(newCard);
      else setIsCompleted(true);
    }
  }, [studyQueue]);

  // 예상 학습 시간 계산하기
  const IPreview = useMemo(() => {
    if (!currentCard) return null;
    return f.repeat(currentCard.fsrsParameters, new Date());
  }, [currentCard, f]);

  return { currentCard, studyQueue, IPreview, repeat, error, isCompleted };
};
