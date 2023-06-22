import styles from "./Boton.module.css";

interface Props {
  texto?: string;
  classname?: string;
  onClickedButton: () => any;
  tooltip?: string;
}

const Boton = ({ classname = "", texto, onClickedButton, tooltip }: Props) => {
  return (
    <>
      <button
        placeholder={tooltip}
        className={`${classname} ${styles.Boton}`}
        onClick={() => onClickedButton()}
      >
        {texto}
      </button>
    </>
  );
};

export default Boton;
