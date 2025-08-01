import { SpinnerCircular } from 'spinners-react';

export default function LoadingSpinner() {
  return (
    <div
      style={{
        position: 'fixed',
        left: 0,
        width: '100vw',
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
        zIndex: 998
      }}
    >
      <SpinnerCircular />
    </div>
  );
}
