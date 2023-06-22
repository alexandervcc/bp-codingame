import { IMovement } from "../models/IMovement.interface";

const HEADERS = {
  Accept: "application/json, text/plain, */*",
  "Content-Type": "application/json;charset=utf-8",
};

export const createNewMovement = async (
  newMovement: IMovement
): Promise<Response> => {
  const bodyReq = JSON.stringify(newMovement);
  console.log("res: ", bodyReq);
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/movimientos/`,
    {
      method: "POST",
      headers: HEADERS,
      body: bodyReq,
    }
  );
  return response;
};

export const updateMovement = async () => {};

export const getAllMovements = async (): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/movimientos/all`,
    {
      method: "GET",
      headers: HEADERS,
    }
  );
  return response;
};

export const getAllMovementsByClient = async (
  clienteName: string,
  fechaInicio: string,
  fechaFin: string
): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/movimientos/?clientName=${clienteName}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`,
    {
      method: "GET",
      headers: HEADERS,
    }
  );
  return response;
};

export const deleteMovementById = async (
  movementId: number
): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/movimientos/?movementId=${movementId}`,
    {
      method: "DELETE",
      headers: HEADERS,
    }
  );
  return response;
};
