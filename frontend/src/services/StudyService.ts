import { UserCard } from '@/types/schemes';
import { Grade, Rating, State } from 'ts-fsrs';

import { postStudyInfo } from '@/api/study';
import { createFSRS } from '@/utils/fsrs';

const STATE_MAP = {
  [State.New]: 'New',
  [State.Review]: 'Review',
  [State.Learning]: 'Learning',
  [State.Relearning]: 'Relearning'
};

export interface StudyCounts {
  reviewCounts: number;
  learningCounts: number;
  overdueCounts: number;
  newCounts: number;
}

export class StudyService {
  private f = createFSRS();
  private _queue: UserCard[] = [];

  constructor(initialQueue: UserCard[]) {
    console.log('initialQueue', initialQueue);
    this.queue = initialQueue;
  }

  public logQueue() {
    this._queue.forEach((c) =>
      console.log(
        `${c.studyInfo.due < new Date() ? 'overdue\t' : 'not overdue\t'} ${c.koreanCard.koreanWord} ${STATE_MAP[c.studyInfo.state]}\t lastRating: ${
          c.studyInfo.lastRating ?? 'null'
        }`
      )
    );
  }

  public get queue(): readonly UserCard[] {
    return this._queue;
  }

  private set queue(newQueue: UserCard[]) {
    this._queue = newQueue.sort((a, b) => this.compareCards(a, b));
    this.logQueue();
  }

  public updateQueue(newCard: UserCard): void {
    const index = this._queue.findIndex((c) => c.userCardId === newCard.userCardId);
    if (index !== -1) {
      this._queue[index] = newCard;
      this._queue.sort(this.compareCards);
    }

    this.logQueue();
  }

  public get hasCards() {
    return this._queue.length > 0;
  }

  public get currentCard() {
    if (!this.hasCards) {
      throw new Error('No cards available in queue');
    }
    return this.queue[0];
  }

  public get isCompleted() {
    return (
      this.hasCards &&
      this.queue.every(
        (card) => card.studyInfo.state === State.Review && card.studyInfo.due > new Date()
      )
    );
  }

  public get iPreview() {
    return this.f.repeat(this.currentCard.studyInfo, new Date());
  }

  public get studyCounts() {
    const now = new Date();
    const reviewCounts = this.queue.filter(
      (card) => card.studyInfo.state === State.Review && card.studyInfo.due >= now
    ).length;
    const learningCounts = this.queue.filter(
      (card) => card.studyInfo.state === State.Learning || card.studyInfo.state === State.Relearning
    ).length;
    const overdueCounts = this.queue.filter(
      (card) => card.studyInfo.state === State.Review && card.studyInfo.due < now
    ).length;
    const newCounts = this.queue.filter((card) => card.studyInfo.state === State.New).length;

    return {
      reviewCounts,
      learningCounts,
      overdueCounts,
      newCounts
    } as StudyCounts;
  }

  private stateFilter(s: State) {
    if (s === State.Relearning) return State.Learning;
    return s;
  }

  // sort를 위한 비교 함수
  private compareCards(a: UserCard, b: UserCard) {
    // overDue 여부로 정렬
    const now = new Date();
    const aIsOverdue = a.studyInfo.due < now;
    const bIsOverdue = b.studyInfo.due < now;
    if (aIsOverdue !== bIsOverdue) return aIsOverdue ? -1 : 1;

    // state(New < Review < Learning = Relearning)로 정렬
    const aState = this.stateFilter(a.studyInfo.state);
    const bState = this.stateFilter(b.studyInfo.state);
    if (aState !== bState) return aState - bState;

    // lastRating(최근 평가 시간)로 정렬
    return (a.studyInfo.lastRating ?? now).getTime() - (b.studyInfo.lastRating ?? now).getTime();
  }

  public repeat(rating: Rating) {
    // 새로운 카드 상태 계산
    const newIPreview = this.iPreview;
    const newRecordLogItem = newIPreview[rating as Grade];

    const newStudyInfo = {
      ...newRecordLogItem.card,
      lastRating: new Date()
    };

    const newCard = { ...this.currentCard, studyInfo: newStudyInfo };
    this.updateQueue(newCard);

    return postStudyInfo(newCard.userCardId, newCard.studyInfo);
  }
}
