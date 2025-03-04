import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { UserCard, CardCategory } from '@/types/schemes';

type StudyQueueState = Partial<Record<CardCategory, UserCard[]>>;

const initialState: StudyQueueState = {};

export const studyQueueSlice = createSlice({
  name: 'studyQueue',
  initialState,
  reducers: {
    updateStudyQueue: (state, action: PayloadAction<StudyQueueState>) => {
      return { ...state, ...action.payload };
    }
  }
});

export const { updateStudyQueue } = studyQueueSlice.actions;
export default studyQueueSlice.reducer;
