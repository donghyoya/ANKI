export const upperSnakeToCamel = (input: string): string => {
    const words = input.toLowerCase().split('_');
    return (
      words[0] +
      words
        .slice(1)
        .map((w) => w.charAt(0).toUpperCase() + w.slice(1))
        .join('')
    );
  };