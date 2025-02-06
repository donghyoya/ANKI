'use client';

import { useEffect, useState } from 'react';

export default function HomePage() {
  const [width, setWidth] = useState(window.innerWidth);

  useEffect(() => {
    function handleResize() {
      setWidth(window.innerWidth);
    }

    window.addEventListener('resize', handleResize);

    return () => window.removeEventListener('resize', handleResize);
  }, []);

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
