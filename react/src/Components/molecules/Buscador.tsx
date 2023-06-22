import React, { useState } from "react";
import Boton from "../atoms/Boton";
import styles from "./Buscador.module.css";

interface Props {
  searchFor: (value: string) => any;
  getAll: () => any;
}

const Buscador = ({ getAll, searchFor }: Props) => {
  const [valueToSearch, setValueToSearch] = useState("");
  return (
    <>
      <input
        className={styles.InputBuscador}
        type="text"
        onChange={(e) => setValueToSearch(e.target.value)}
      />
      <Boton
        classname={styles.BotonBuscar}
        texto="Buscar"
        onClickedButton={() => searchFor(valueToSearch)}
      />
      <Boton
        classname={styles.BotonLimpiar}
        texto="Limpiar"
        onClickedButton={getAll}
      />
    </>
  );
};

export default Buscador;
