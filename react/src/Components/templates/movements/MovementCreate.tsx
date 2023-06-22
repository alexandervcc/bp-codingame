import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { IMovement } from "../../../models/IMovement.interface";
import { getAccountById } from "../../../services/Account.service";
import { createNewMovement } from "../../../services/Movements.service";
import { isNumber } from "../../../utils/Validation";

const MovementCreate = () => {
  const queryParams = new URLSearchParams(window.location.search);
  const cuentaId = queryParams.get("cuentaId") as string;
  const navigate = useNavigate();

  const { register, handleSubmit } = useForm<IMovement>();

  useEffect(() => {
    const obtenerCuenta = async () => {
      if (!isNumber(cuentaId)) {
        navigate("/404?message=Id+de+cuenta+invalido.");
      }
      const respuesta = await getAccountById(+cuentaId);
      if (!respuesta.ok) {
        navigate("/404?message=Cuenta+a+crear+movimiento+no+existe.");
      }
    };
    obtenerCuenta();
  });

  const submitCreateNewAMovement = async (newMovement: IMovement) => {
    newMovement.cuentaOrigen = +cuentaId;

    newMovement.valor = +newMovement.valor;

    const response = await createNewMovement(newMovement);
    const bodyRes = await response.json();
    if (response.ok) {
      alert(bodyRes.title);
      navigate("/cuentas");
    } else {
      alert(`${bodyRes.title}: ${bodyRes.error}`);
    }
  };

  return (
    <div>
      <h2>Crear Movimiento</h2>
      <div>Cuenta: {cuentaId}</div>
      <hr />
      <form onSubmit={handleSubmit(submitCreateNewAMovement)}>
        <div>
          <label>Valor: </label>
          <input
            type="number"
            min={1}
            step={0.01}
            {...register("valor", { required: true })}
          />
        </div>
        <div>
          <label>Tipo de Movimiento: </label>
          <input
            type="radio"
            value="DEBITO"
            {...register("tipoMovimiento", { required: true })}
          />
          Debito
          <input
            type="radio"
            value="CREDITO"
            {...register("tipoMovimiento", { required: true })}
          />
          Credito
        </div>

        <input type="submit" value="Crear Movimiento" />
      </form>
    </div>
  );
};

export default MovementCreate;
