import Dialog from '@/components/material-components/Dialog';
import { Icon, IconButton } from '../material-components/IconButton/IconButton';
import TextButton from '../material-components/TextButton';

interface CustomDialogProps {
  open: boolean;
  headline: string;
  prompt: React.ReactNode;
  firstButtonString?: string;
  secondButtonString?: string;
  onCancel?: () => void;
  firstButtonOnclick?: () => void;
  secondButtonOnclick?: () => void;
}

const CustomDialog = ({
  open,
  headline,
  prompt,
  firstButtonString,
  secondButtonString,
  onCancel,
  firstButtonOnclick,
  secondButtonOnclick
}: CustomDialogProps) => {
  return (
    <Dialog open={open} onCancel={onCancel} noFocusTrap>
      <span slot="headline">
        <span>{headline}</span>
        {onCancel && (
          <IconButton value="cancel" aria-label="Cancel dialog" onClick={onCancel}>
            <Icon>close</Icon>
          </IconButton>
        )}
      </span>
      <form id="form" slot="content" method="dialog">
        {prompt}
      </form>
      <div slot="actions">
        {firstButtonString && (
          <TextButton onClick={firstButtonOnclick}>{firstButtonString}</TextButton>
        )}
        {secondButtonString && (
          <TextButton onClick={secondButtonOnclick}>{secondButtonString}</TextButton>
        )}
      </div>
    </Dialog>
  );
};

export default CustomDialog;
