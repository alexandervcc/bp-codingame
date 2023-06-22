import { useEffect, useState } from "react";
import { IClient } from "../../../models/IClient.interface";
import { IMovement } from "../../../models/IMovement.interface";
import { IMovementReport } from "../../../models/IMovementReport.interface";
import { ResponseDto } from "../../../models/ResponseDto.interface";
import {
  getAllMovements,
  deleteMovementById,
  getAllMovementsByClient,
} from "../../../services/Movements.service";
import {
  downloadPDFMovementsReportByClient,
  openPDFInNewTab,
} from "../../../services/Report.service";
import { checkValidArguments_MovementsSearch } from "../../../utils/Validation";
import Boton from "../../atoms/Boton";
import TableDataRow from "../../atoms/TableDataRow";
import BuscadorFechas from "../../molecules/BuscadorFechas";

import styles from "./Movement.module.css";

const Movement = () => {
  const [listMovements, setListMovements] = useState<IMovement[]>();
  const [clientMovement, setClientMovement] = useState<IClient>();

  const downloadAllMovements = async () => {
    const response = await getAllMovements();
    if (response.ok) {
      const bodyRes = (await response.json()) as ResponseDto;
      const movements = bodyRes.data["movimientos"] as IMovement[];
      movements.forEach((mov) => (mov.fecha = new Date(mov.fecha)));
      setListMovements(movements);
    }
  };

  useEffect(() => {
    downloadAllMovements();
  }, []);

  const handleMovememntDelete = async (movementId: number) => {
    const response = await deleteMovementById(movementId);
    const bodyRes = (await response.json()) as ResponseDto;
    if (response.ok) {
      alert("Info: " + bodyRes.title);
    } else {
      alert("Error: " + bodyRes.error);
    }
  };

  const handleMovementsSearch = async (
    nombreCliente: string,
    initialDate: string,
    finalDate: string
  ) => {
    if (
      !checkValidArguments_MovementsSearch(
        nombreCliente,
        initialDate,
        finalDate
      )
    ) {
      return;
    }

    const response = await getAllMovementsByClient(
      nombreCliente,
      initialDate,
      finalDate
    );
    const resBody = (await response.json()) as ResponseDto;
    if (!response.ok) {
      alert(`Error: ${resBody.error}`);
      return;
    }
    const listMovements = resBody.data as IMovementReport;
    listMovements.movimientos.forEach(
      (mov) => (mov.fecha = new Date(mov.fecha))
    );
    setListMovements(listMovements.movimientos);
    setClientMovement(listMovements.cliente);
  };

  const downloadPDFRerport = async (
    nombreCliente: string,
    initialDate: string,
    finalDate: string
  ) => {
    if (
      !checkValidArguments_MovementsSearch(
        nombreCliente,
        initialDate,
        finalDate
      )
    ) {
      return;
    }
    const response = await downloadPDFMovementsReportByClient(
      nombreCliente,
      initialDate,
      finalDate
    );
    if (!response.ok) {
      const resBody = (await response.json()) as ResponseDto;
      alert(`${resBody.title}: ${resBody.error}`);
      return;
    }
    const pdfBlob = await response.blob();
    openPDFInNewTab(pdfBlob);
  };

  return (
    <div>
      <h2>Movimientos</h2>
      <BuscadorFechas
        getAll={downloadAllMovements}
        searchFor={handleMovementsSearch}
        downloadReport={downloadPDFRerport}
      />
      <hr />
      <div>
        <h3>Cantidad de movimientos: {listMovements?.length}</h3>
        <div>
          <table className={styles.table}>
            <thead>
              <tr className={styles.tableHeader}>
                <th>Fecha</th>
                <th>Cliente</th>
                <th>Tipo Cuenta</th>
                <th>Cuenta</th>
                <th>Tipo Movimiento</th>
                <th>Saldo Inicial</th>
                <th>Valor</th>
                <th>Saldo</th>
                <th>Accion</th>
              </tr>
            </thead>
            <tbody>
              {listMovements?.map((movement) => (
                <TableDataRow
                  key={movement.id}
                  movement={movement}
                  clientName={clientMovement?.nombre}
                  deleteRowData={deleteMovementById}
                  urlEditRowData="movimientos"
                />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Movement;
