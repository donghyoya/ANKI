'use client';

import { useWindowSize } from '@/hooks/useWindowSize';

export default function HomePage() {
  const width = useWindowSize();

  const GridItem = ({ color }: { color: string }) => (
    <div
      className="grid-item"
      style={{ backgroundColor: color, gridColumn: 'span 1', opacity: 0.5 }}
    />
  );

  const GridItems = () => (
    <>
      <GridItem color="red" />
      <GridItem color="blue" />
      <GridItem color="green" />
      <GridItem color="yellow" />
    </>
  );

  return (
    <div className="grid-container">
      <GridItems />
      {width > 600 && <GridItems />}
      {width > 840 && <GridItems />}
    </div>
  );
}
