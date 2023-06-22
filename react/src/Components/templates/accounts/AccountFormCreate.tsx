import  { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate} from "react-router-dom";
import { IAccount } from "../../../models/IAccount.interface";
import { IClient } from "../../../models/IClient.interface";
import { ResponseDto } from "../../../models/ResponseDto.interface";
import {
  createNewAccountForUser,
} from "../../../services/Account.service";
import { getClientById } from "../../../services/Client.service";
import { isNumber } from "../../../utils/Validation";

const AccountFormCreate = () => {
  const queryParams = new URLSearchParams(window.location.search);
  const clienteId = queryParams.get("cliente") as string;
  const navigate = useNavigate();
  const [client, setClient] = useState<IClient>();

  const { register, handleSubmit } = useForm<IAccount>();

  useEffect(() => {
    const obtenerCliente = async () => {
      if (!isNumber(clienteId)) {
        navigate("/404?message=Id+Invalido");
      }
      const respuesta = await getClientById(clienteId);
      if (!respuesta.ok) {
        navigate("/404?message=Cliente+a+crear+cuenta+no+existe.");
      }
      const reponseBody = (await respuesta.json()) as ResponseDto;
      const clienteACrearCuenta = reponseBody.data as IClient;
      setClient(clienteACrearCuenta);
    };
    obtenerCliente();
  });

  const submitCreateNewAccount = async (newAccount: IAccount) => {
    const response = await createNewAccountForUser(+clienteId, newAccount);
    const bodyRes = await response.json();
    if (response.ok) {
      alert(bodyRes.title);
      navigate("/cuentas");
    } else {
      alert(`${bodyRes.title}: ${bodyRes.error}`);
    }
  };

  return (
    <>
      <div>
        <h2>Crear Cuenta</h2>
        <div>Cliente: {client?.nombre}</div>
        <hr />
        <form onSubmit={handleSubmit(submitCreateNewAccount)}>
          <div>
            <label>Tipo de Cuenta: </label>
            <input
              type="radio"
              value="AHORROS"
              {...register("tipoDeCuenta", { required: true })}
            />
            Ahorros
            <input
              type="radio"
              value="CORRIENTE"
              {...register("tipoDeCuenta", { required: true })}
            />
            Corriente
          </div>

          <div>
            <label>Saldo Inicial: </label>
            <input
              type="number"
              min={0}
              step={0.01}
              {...register("saldoInicial", { required: true, min: 8 })}
            />
          </div>
          <div>
            <label>Estado (Activa): </label>
            <input type="checkbox" {...register("estado")} />
          </div>

          <input type="submit" value="Crear Cuenta" />
        </form>
      </div>
    </>
  );
};

export default AccountFormCreate;
