import { Route, Routes } from "react-router-dom";
import styles from "./App.module.css";
import Header from "./Components/atoms/Header";
import SideNavigator from "./Components/organisms/SideNavigator";
import Account from "./Components/templates/accounts/Account";
import AccountFormCreate from "./Components/templates/accounts/AccountFormCreate";
import AccountFormEdit from "./Components/templates/accounts/AccountFormEdit";
import ClientFormCreate from "./Components/templates/clients/ClientFormCreate";
import ClientFormEdit from "./Components/templates/clients/ClientFormEdit";
import Clients from "./Components/templates/clients/Clients";
import Main from "./Components/templates/Main";
import Movement from "./Components/templates/movements/Movement";
import MovementCreate from "./Components/templates/movements/MovementCreate";
import MovementEdit from "./Components/templates/movements/MovementEdit";
import Route404 from "./Components/templates/Route404";

function App() {
  return (
    <>
      <Header />
      <div className={styles.Container}>
        <div className={styles.Row}>
          <div className={styles.SideBar}>
            <SideNavigator />
          </div>
          <div className={styles.Content}>
            <Routes>
              <Route path="/" element={<Main />} />
              <Route path="clientes" element={<Clients />}></Route>
              <Route path="clientes/:clienteId" element={<ClientFormEdit />} />
              <Route path="clientes/nuevo" element={<ClientFormCreate />} />

              <Route path="cuentas" element={<Account />} />
              <Route path="cuentas/new/" element={<AccountFormCreate />} />
              <Route path="cuentas/:cuentaId" element={<AccountFormEdit />} />

              <Route path="movimientos" element={<Movement />} />
              <Route path="movimientos/new" element={<MovementCreate />} />
              <Route path="movimientos/:movimientoId" element={<MovementEdit />} />


              <Route path="*" element={<Route404 />} />
            </Routes>
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
