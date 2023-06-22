import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { IClient } from "../../../models/IClient.interface";
import {
  getAllClients,
  searchClientByName,
} from "../../../services/Client.service";
import Buscador from "../../molecules/Buscador";
import ClientHolder from "../../molecules/ClientHolder";
import styles from "./Clients.module.css";

const getClientes = async (callback: (param: IClient[]) => any) => {
  const fetchedClients = await getAllClients();
  const listClients = fetchedClients.dataList as IClient[];
  callback(listClients);
};

const Clients = () => {
  const [listaClientes, setListaClientes] = useState<IClient[]>([]);

  const getAllClients = useCallback(async () => {
    getClientes(setListaClientes);
  }, []);

  useEffect(() => {
    getAllClients();
  }, [getAllClients]);

  const searchForClients = async (nombreBuscar: string) => {
    if (nombreBuscar.length === 0) {
      alert("Nombre a buscar vacio.");
      return;
    }
    const clientsFound = await searchClientByName(nombreBuscar);
    const listClients = clientsFound.dataList as IClient[];
    setListaClientes(listClients);
  };

  return (
    <div>
      <h2>Clientes</h2>
      <div className={styles.BotonNavegacion}>
        <Link className={styles.link} to="/clientes/nuevo">
          Crear Cliente
        </Link>
      </div>
      <div className={styles.Buscador}>
        <Buscador searchFor={searchForClients} getAll={getAllClients} />
      </div>
      <div className={styles.Resultados}>
        {listaClientes.length > 0 ? (
          listaClientes.map((cliente) => (
            <ClientHolder key={cliente.id} cliente={cliente} />
          ))
        ) : (
          <p>No hay clientes para mostrar</p>
        )}
      </div>
    </div>
  );
};

export default Clients;
