import { useState } from "react";
import Boton from "../atoms/Boton";
import styles from "./BuscadorFechas.module.css";

interface Props {
  searchFor: (value: any, startDate: any, finalDate: string) => any;
  getAll: () => any;
  downloadReport: (value: any, startDate: any, finalDate: string) => any;
}

const BuscadorFechas = ({ getAll, searchFor, downloadReport }: Props) => {
  const [valueToSearch, setValueToSearch] = useState("");
  const [initialDate, setInitialDate] = useState<any>();
  const [finalDate, setFinalDate] = useState<any>();

  return (
    <div className={styles.buscador}>
      <input
        className={styles.InputBuscador}
        type="text"
        placeholder="Nombre Cliente: "
        onChange={(e) => setValueToSearch(e.target.value)}
      />
      <label>Fecha Inicio: </label>
      <input
        type="date"
        className={styles.datePicker}
        onChange={(e) => setInitialDate(`${e.target.value}T00:00:00`)}
      />
      <label>Fecha Fin : </label>
      <input
        type="date"
        className={styles.datePicker}
        onChange={(e) => setFinalDate(`${e.target.value}T23:59:59`)}
      />

      <Boton
        classname={styles.BotonBuscar}
        texto="Buscar"
        onClickedButton={() => searchFor(valueToSearch, initialDate, finalDate)}
      />
      <Boton
        classname={styles.BotonLimpiar}
        texto="Limpiar"
        onClickedButton={() => getAll()}
      />

      <Boton
        classname={styles.botonDescargar}
        texto="Descargar Reporte"
        onClickedButton={() => downloadReport(valueToSearch, initialDate, finalDate)}
      />
    </div>
  );
};

export default BuscadorFechas;
