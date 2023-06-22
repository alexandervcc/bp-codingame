import { useForm } from "react-hook-form";
import { IClient } from "../../../models/IClient.interface";
import { ResponseDto } from "../../../models/ResponseDto.interface";
import { createClient } from "../../../services/Client.service";

import styles from "./ClientFormCreate.module.css";

interface Props {
  cliente?: IClient;
}

const ClientFormCreate = ({ cliente }: Props) => {

  const {
    register,
    handleSubmit,
  } = useForm<IClient>();

  const onSubmit = async (newClient: IClient) => {
    const response = await createClient(newClient);
    const body = (await response.json()) as ResponseDto;
    if (response.ok) {
      alert(body.title);
    } else {
      alert(`${body.title}: ${body.error}`);
    }
  };

  return (
    <>
      <div>
        <h2>Crear Cliente</h2>
        <hr />
        <div className={styles.form}></div>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div>
            <label>Identificacion</label>
            <input
              type="text"
              {...register("identificacion", {
                required: true,
                minLength: 10,
              })}
              placeholder="1712345678"
            />
          </div>
          <div>
            <label>Nombre</label>
            <input
              type="text"
              {...register("nombre", { required: true, minLength: 10 })}
              placeholder="Cosme Fulanito"
            />
          </div>
          <div>
            <label>Direccion:</label>
            <input
              type="text"
              {...register("direccion", { required: true, minLength: 10 })}
              placeholder="Inaquito"
            />
          </div>
          <div>
            <label>Telefono</label>
            <input
              type="text"
              {...register("telefono", {
                required: true,
                minLength: 7,
                maxLength: 10,
              })}
              placeholder="0987654321 | 2222222"
            />
          </div>
          <div>
            <label>Edad:</label>
            <input
              type="number"
              min={18}
              {...register("edad", { required: true, min: 18, max: 99 })}
              placeholder="18"
            />
          </div>
          <div>
            <label>Genero:</label>
            <input
              type="radio"
              value="M"
              {...register("genero", { required: true })}
            />
            Masculino
            <input
              type="radio"
              value="F"
              {...register("genero", { required: true })}
            />
            Femenino
          </div>
          <div>
            <label>
              Contrasena:
              <input
                type="password"
                {...register("contrasena", { required: true, min: 8 })}
                placeholder="***********"
              />
            </label>
          </div>
          <div>
            <label>
              Estado:
              <input
                type="radio"
                value="true"
                {...register("estado", { required: true })}
              />
              Activado
              <input
                type="radio"
                value="false"
                {...register("estado", { required: true })}
              />
              Desactivado
            </label>
          </div>
          <input type="submit" />
        </form>
      </div>
    </>
  );
};

export default ClientFormCreate;
