import { configureStore } from '@reduxjs/toolkit';
import studyQueueReducer from './slices/studySlice';

export const store = configureStore({
  reducer: {
    studyQueue: studyQueueReducer
  },
  devTools: process.env.NODE_ENV !== 'production'
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
