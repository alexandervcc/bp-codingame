import { Link } from "react-router-dom";
import { IAccount } from "../../models/IAccount.interface";
import { ResponseDto } from "../../models/ResponseDto.interface";
import { deleteAccountById } from "../../services/Account.service";
import Boton from "../atoms/Boton";
import styles from "./ClientHolder.module.css";

interface Props {
  cuenta: IAccount;
}

const AccountHolder = ({ cuenta }: Props) => {
  const handleOnDeleteButton = async (accountId?: number) => {
    if (!accountId) {
      return;
    }
    const response = await deleteAccountById(accountId);
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
          <b>Numero de cuenta: </b> {cuenta.numeroDeCuenta}
        </p>
        <p className={styles.ClientData}>
          <b> Tipo de Cuenta:</b> {cuenta.tipoDeCuenta}
        </p>
        <p className={styles.ClientData}>
          <b>Saldo:</b> {cuenta.saldoInicial}
        </p>
        <p className={styles.ClientData}>
          <b>Activada(Estado):</b> {cuenta.estado ? "Activa" : "Inactiva"}
        </p>
      </div>
      <div className={styles.BotonContainer}>
        <Link
          className={styles.link}
          to={`/movimientos/new?cuentaId=${cuenta.numeroDeCuenta}`}
        >
          <Boton
            classname={styles.BotonAnadir}
            texto="Movimiento"
            onClickedButton={() => {}}
          />
        </Link>
        <Link className={styles.link} to={`/cuentas/${cuenta.numeroDeCuenta}`}>
          <Boton
            classname={styles.BotonEditar}
            texto="Editar"
            onClickedButton={() => {}}
          />
        </Link>
        <Boton
          classname={styles.BotonEliminar}
          texto="Eliminar"
          onClickedButton={() => handleOnDeleteButton(cuenta.numeroDeCuenta)}
        />
      </div>
    </div>
  );
};

export default AccountHolder;
