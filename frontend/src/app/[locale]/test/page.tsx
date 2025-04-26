import { convertQuery } from '@/utils/converter';

const TestPage = () => {
  console.log(convertQuery('action'));

  return <div>TestPage</div>;
};

export default TestPage;
