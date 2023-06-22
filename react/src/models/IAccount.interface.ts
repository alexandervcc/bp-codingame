import { IClient } from "./IClient.interface";

export interface IAccount {
  clienteId?:string;
  numeroDeCuenta: number;
  tipoDeCuenta: string;
  saldoInicial: number;
  estado: boolean;
  cliente?: IClient;
  movimientos: any[];
}
