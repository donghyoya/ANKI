import TooltipProvider from './TooltipProvider';

import styles from './TooltipExample.module.scss';
import { IconButton, Icon, FilledIconButton } from '../material-components/IconButton/IconButton';

const exampleText = '안녕하세요';

const TooltipsExample = () => {
  return (
    <div className={styles['container']}>
      <p>요소 간 시각적인 구분이 있는 경우 기본 간격 (4px)</p>
      <TooltipProvider text={exampleText}>
        <FilledIconButton>
          <Icon>check</Icon>
        </FilledIconButton>
      </TooltipProvider>

      <p>요소 간 시각적인 구분이 없는 경우 Provider에 extraGap=true를 전달 (8px)</p>
      <TooltipProvider text={exampleText} extraGap={true}>
        <IconButton>
          <Icon>check</Icon>
        </IconButton>
      </TooltipProvider>
    </div>
  );
};

export default TooltipsExample;
