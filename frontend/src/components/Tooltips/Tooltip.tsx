import styles from './Tooltip.module.scss';

const Tooltip = ({ text, rect, extraGap }: { text: string; rect: DOMRect; extraGap?: boolean }) => {
  return (
    <div
      className={styles['tooltip']}
      data-tooltip={text}
      {...(extraGap && { 'data-extra-gap': true })}
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
