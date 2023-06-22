import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { IAccount } from "../../../models/IAccount.interface";
import { ResponseDto } from "../../../models/ResponseDto.interface";
import {
  getAccountById,
  updateAccount,
} from "../../../services/Account.service";
import { isNumber } from "../../../utils/Validation";

const AccountFormEdit = () => {
  const params = useParams();
  const navigate = useNavigate();
  const cuentaId = params.cuentaId!!;

  const { register, handleSubmit, setValue } = useForm<IAccount>();

  useEffect(() => {
    const obtenerCuenta = async () => {
      if (!isNumber(cuentaId)) {
        navigate("/404");
      }
      const respuesta = await getAccountById(+cuentaId);
      if (!respuesta.ok) {
        navigate("/404");
      }
      const reponseBody = (await respuesta.json()) as ResponseDto;
      const cuentaAEditar = reponseBody.data as IAccount;
      setValue("estado", cuentaAEditar.estado);
      setValue("saldoInicial", cuentaAEditar.saldoInicial);
      setValue("tipoDeCuenta", cuentaAEditar.tipoDeCuenta);
    };
    obtenerCuenta();
  });

  const onSubmit = async (newClient: IAccount) => {
    console.log("actualizando: ", newClient);
    const response = await updateAccount(+cuentaId, newClient);
    const body = (await response.json()) as ResponseDto;
    console.log("res:", body);
    if (response.ok) {
      alert(body.title);
    } else {
      if (body.error) {
        alert(`${body.title}: ${body.error}`);
      } else {
        const listaErrores = body.errorsList;
        let errores = "";
        Object.keys(listaErrores).forEach(
          (k) => (errores += k + ": " + listaErrores[k])
        );
        alert(`${body.title}: ${errores}`);
      }
    }
  };

  return (
    <>
      <div>
        <h2>Editar Cuenta</h2>
        <div>Cliente:</div>
        <hr />
        <form onSubmit={handleSubmit(onSubmit)}>
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

          <input type="submit" value="Actualizar Cuenta" />
        </form>
      </div>
    </>
  );
};

export default AccountFormEdit;
