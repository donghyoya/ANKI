import { configureStore } from '@reduxjs/toolkit';
import studyQueueReducer from './slices/studySlice';
import authReducer from './slices/authSlice';

export const store = configureStore({
  reducer: {
    studyQueue: studyQueueReducer,
    auth: authReducer
  },
  devTools: process.env.NODE_ENV !== 'production'
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
