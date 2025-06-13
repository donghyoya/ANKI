import { useEffect, useState } from 'react';
import { createFSRS } from '@/utils/FSRS';

import { getKoreanCardDetail } from '@/api/cards';
import { getLearningCards, postStudyInfo } from '@/api/study';

import { UserCard, StudyInfo, KoreanCardDetail } from '@/types/schemes';
import { Category } from '@/types/Category';
import { IPreview, Rating, State } from 'ts-fsrs';

export const useStudyQueue = (category: Category) => {
  const [studyQueue, setStudyQueue] = useState<UserCard[] | null>(null);
  const [currentCard, setCurrentCard] = useState<UserCard | null>(null);
  const [currentCardDetail, setCurrentCardDetail] = useState<KoreanCardDetail | null>(null);
  const [isCompleted, setIsCompleted] = useState(false);

  const f = createFSRS();

  // 학습 큐 우선순위:
  // 1. 새로운 카드 (State.New)
  // 2. 기한이 지난 카드 (due < 현재 시간)
  // 3. 학습 중이거나 재학습 중인 카드 (Learning/Relearning)
  const getNextCard = (studyQueue: UserCard[]) => {
    let nextCard;

    // new인 카드 찾기
    nextCard = studyQueue.find((c) => c.studyInfo.state === State.New);
    if (nextCard) return nextCard;

    // overdue인 카드 찾기
    nextCard = studyQueue.find((c) => c.studyInfo.due < new Date());
    if (nextCard) return nextCard;

    // learning, re-learning인 카드 찾기
    nextCard = studyQueue.find(
      (c) => c.studyInfo.state === State.Learning || c.studyInfo.state === State.Relearning
    );
    return nextCard;
  };

  const repeat = async (rating: Rating) => {
    // 에러 처리
    if (!currentCard) {
      throw new Error('CURRENT_CARD_NOT_FOUND');
    }
    if (!studyQueue) {
      throw new Error('STUDY_QUEUE_NOT_FOUND');
    }

    // 새로운 카드 상태 계산
    const newIPreview = f.repeat(currentCard.studyInfo, new Date());
    const newRecordLogItem = newIPreview[rating as keyof IPreview];

    const newStudyInfo: StudyInfo =
      typeof newRecordLogItem === 'function'
        ? newRecordLogItem().next().value.card
        : newRecordLogItem.card;

    const newCard = { ...currentCard, studyInfo: newStudyInfo } as UserCard;
    console.log('newCard', newCard);
    updateStudyInfo(newCard);
  };

  const updateStudyInfo = async (newCard: UserCard) => {
    // 서버에 카드 상태 업데이트
    await postStudyInfo(newCard.userCardId, newCard.studyInfo);

    // 로컬 상태 업데이트
    setStudyQueue(
      studyQueue?.map((c) => (c.userCardId === newCard.userCardId ? newCard : c)) ?? null
    );
  };

  // 학습 큐 요청하기
  useEffect(() => {
    const fetchCards = async () => {
      const newCards = await getLearningCards('new', category);
      const reviewCards = await getLearningCards('review', category);
      setStudyQueue([...newCards.content, ...reviewCards.content]);
    };
    fetchCards();
  }, [category]);

  // 학습 큐가 변경될 때마다:
  // 1. 다음 학습할 카드를 결정
  // 2. 해당 카드의 상세 정보를 가져옴
  // 3. 더 이상 학습할 카드가 없으면 완료 상태로 변경
  useEffect(() => {
    // 카드 상세 정보 가져오기
    async function fetchCardDetail(userCard: UserCard) {
      if (studyQueue) {
        const newCardDetail = await getKoreanCardDetail(userCard.koreanCard.cardId);
        setCurrentCardDetail(newCardDetail);
      }
    }

    // 현재 카드 설정하기
    if (studyQueue) {
      const nextCard = getNextCard(studyQueue);
      if (nextCard) {
        setCurrentCard(nextCard);
        fetchCardDetail(nextCard);
      } else {
        setIsCompleted(true);
      }
    }
  }, [studyQueue]);

  // 예상 학습 시간 계산하기
  const iPreview = currentCard ? f.repeat(currentCard.studyInfo, new Date()) : null;

  return { currentCard, currentCardDetail, studyQueue, iPreview, repeat, isCompleted };
};
