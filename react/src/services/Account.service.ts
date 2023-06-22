import { IAccount } from "../models/IAccount.interface";
import { ResponseDto } from "../models/ResponseDto.interface";

const HEADERS = {
  Accept: "application/json, text/plain, */*",
  "Content-Type": "application/json;charset=utf-8",
  "Access-Control-Allow-Origin": "*",
};


export const getAccountById = async (accountId: number): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/cuentas/?accountId=${accountId}`,
    {
      method: "GET",
      headers: HEADERS,
    }
  );
  return response;
};

export const getAllAccounts = async (): Promise<ResponseDto> => {
  const response = await fetch(`${process.env.REACT_APP_API_URL}/cuentas/all`, {
    method: "GET",
    headers: HEADERS,
  });
  const allClients = await response.json();

  return allClients as ResponseDto;
};

export const updateAccount = async (
  accountId: number,
  accountData: IAccount
): Promise<Response> => {
  const bodyReq = {
    ...accountData,
    clienteId: accountId,
    saldoInicial: +accountData.saldoInicial,
  };

  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/cuentas/?accountId=${accountId}`,
    {
      method: "PUT",
      headers: HEADERS,
      body: JSON.stringify(bodyReq),
    }
  );
  return response;
};

export const createNewAccountForUser = async (
  clienteId: number,
  accountData: IAccount
): Promise<Response> => {
  const bodyReq = {
    ...accountData,
    clienteId,
    saldoInicial: +accountData.saldoInicial,
  };

  const response = await fetch(`${process.env.REACT_APP_API_URL}/cuentas/`, {
    method: "POST",
    headers: HEADERS,
    body: JSON.stringify(bodyReq),
  });
  return response;
};

export const deleteAccountById = async (
  accountId: number
): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/cuentas/?accountId=${accountId}`,
    {
      method: "DELETE",
      headers: HEADERS,
    }
  );
  return response;
};
