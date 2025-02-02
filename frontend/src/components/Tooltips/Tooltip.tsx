import styles from './Tooltip.module.scss';

const Tooltip = ({ text, rect }: { text: string; rect: DOMRect }) => {
  return (
    <div
      className={styles['tooltip']}
      data-tooltip={text}
      style={{
        top: rect.top,
        left: rect.left,
        width: rect.width,
        height: rect.height
      }}
    ></div>
  );
};

export default Tooltip;
