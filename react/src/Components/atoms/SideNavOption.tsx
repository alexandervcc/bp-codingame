import { Link } from "react-router-dom";
import styles from "./SideNavOption.module.css";

interface Props {
  LINK_URL: string;
  title: string;
}

const SideNavOption = ({ title, LINK_URL }: Props) => {
  return (
    <div className={styles.listElement}>
      <Link className={styles.link} to={`/${LINK_URL}`}>
        {title}
      </Link>
    </div>
  );
};

export default SideNavOption;
