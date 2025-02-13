'use client';

import { useState, useEffect } from "react";

export function useWindowSize() {
  const [width, setWidth] = useState<number>(1024);

  useEffect(() => {
    if (typeof window === "undefined") return;

    const handleResize = () => {
      setWidth(window.innerWidth);
    };

    handleResize();

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return width;
}
