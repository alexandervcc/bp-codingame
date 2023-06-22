import { useEffect, useState } from "react";
import { IAccount } from "../../../models//IAccount.interface";
import { getAllAccounts } from "../../../services/Account.service";
import styles from "./Account.module.css";
import AccountHolder from "../../molecules/AccountHolder";

const Account = () => {
  const [listaCuentas, setListaCuentas] = useState<IAccount[]>([]);

  useEffect(() => {
    const getAllAcounts = async () => {
      const fetchedAccounts = await getAllAccounts();
      const listAccounts = fetchedAccounts.dataList as IAccount[];
      setListaCuentas(listAccounts);
    };
    getAllAcounts();
  });

  return (
    <div>
      <h2>Cuentas</h2>
      <div className={styles.Resultados}>
        {listaCuentas.length > 0 ? (
          listaCuentas.map((cuenta) => (
            <AccountHolder key={cuenta.numeroDeCuenta} cuenta={cuenta} />
          ))
        ) : (
          <p>No hay cuentas para mostrar</p>
        )}
      </div>
    </div>
  );
};

export default Account;
