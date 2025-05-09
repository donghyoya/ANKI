import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { UserCard } from '@/types/schemes';
import { Category } from '@/types/Category';

type StudyQueueState = Partial<Record<Category, UserCard[]>>;

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
