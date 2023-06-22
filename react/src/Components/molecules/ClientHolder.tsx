import { Link } from "react-router-dom";
import { IClient } from "../../models/IClient.interface";
import { ResponseDto } from "../../models/ResponseDto.interface";
import { deleteClientById } from "../../services/Client.service";
import Boton from "../atoms/Boton";
import styles from "./ClientHolder.module.css";

interface Props {
  cliente: IClient;
}

const ClientHolder = ({ cliente }: Props) => {
  const handleOnDeleteButton = async (clientId?: number) => {
    if (!clientId) {
      return;
    }
    const response = await deleteClientById(clientId);
    const body = (await response.json()) as ResponseDto;
    if (response.ok) {
      alert(body.title);
    } else {
      alert(`${body.title}: ${body.error}`);
    }
  };

  return (
    <div className={styles.Card}>
      <div className={styles.DataHolder}>
        <p className={styles.ClientData}>
          <b>Nombre:</b> {cliente.nombre}
        </p>
        <p className={styles.ClientData}>
          <b> Identificacion:</b> {cliente.identificacion}
        </p>
        <p className={styles.ClientData}>
          <b>Direccion:</b> {cliente.direccion}
        </p>
        <p className={styles.ClientData}>
          <b>Telefono:</b> {cliente.telefono}
        </p>
        <p className={styles.ClientData}>
          <b>Edad</b> {cliente.edad}
        </p>
      </div>
      <div className={styles.BotonContainer}>
        <Link
          className={styles.link}
          to={`/cuentas/new/?cliente=${cliente.id}`}
        >
          <Boton
            classname={styles.BotonAnadir}
            texto="Crear Cuenta"
            onClickedButton={() => {}}
          />
        </Link>
        <Link className={styles.link} to={`/clientes/${cliente.id}`}>
          <Boton
            classname={styles.BotonEditar}
            texto="Editar"
            onClickedButton={() => {}}
          />
        </Link>
        <Boton
          classname={styles.BotonEliminar}
          texto="Eliminar"
          onClickedButton={() => handleOnDeleteButton(cliente.id)}
        />
      </div>
    </div>
  );
};

export default ClientHolder;
