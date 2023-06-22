import { Link } from "react-router-dom";
import Logo from "../../images/Logo.jpg";
import styles from "./Header.module.css";

const Header = () => {
  return (
    <header>
      <div className={styles.header}>
        <Link to={"/"}>
          <img className={styles.imgLogo} src={Logo} alt="logo" />
        </Link>
      </div>
    </header>
  );
};

export default Header;
