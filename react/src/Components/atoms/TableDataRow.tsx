import { Link } from "react-router-dom";
import { IMovement } from "../../models/IMovement.interface";
import Boton from "./Boton";

import styles from "./TableDataRow.module.css";

interface Props {
  movement: IMovement;
  clientName?: string;
  urlEditRowData: string;
  deleteRowData: (idRow: any) => any;
}

const TableDataRow = ({
  movement,
  clientName,
  deleteRowData,
  urlEditRowData,
}: Props) => {
  return (
    <tr key={movement.id}>
      <td>{movement.fecha.toLocaleString()}</td>
      <td>{movement.cliente ? movement.cliente : clientName}</td>
      <td>{movement.tipoCuentaOrigen}</td>
      <td>{movement.cuentaOrigen}</td>
      <td>{movement.tipoMovimiento}</td>
      <td>{movement.saldoInicial}</td>
      <td>{movement.valor}</td>
      <td>{movement.saldoDisponible}</td>
      <td>
        <Link to={`/${urlEditRowData}/${movement.id}`}>
          <Boton
            tooltip="Editar"
            classname={`${styles.circleButton} ${styles.editButton}`}
            onClickedButton={() => {}}
            texto="√"
          />
        </Link>
        <Boton
          tooltip="Eliminar"
          texto="x"
          classname={`${styles.circleButton} ${styles.deleteButton}`}
          onClickedButton={() => deleteRowData(movement.id)}
        />
      </td>
    </tr>
  );
};

export default TableDataRow;
