import SideNavOption from "../atoms/SideNavOption";
import styles from "./SideNavigator.module.css";
const SideNavigator = () => {
  return (
    <div className={styles.sideNav}>
      <SideNavOption title="Clientes" LINK_URL="clientes" />
      <SideNavOption title="Cuentas" LINK_URL="cuentas" />
      <SideNavOption title="Movimientos" LINK_URL="movimientos" />
    </div>
  );
};

export default SideNavigator;
