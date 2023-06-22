import { IClient } from "./IClient.interface";
import { IMovement } from "./IMovement.interface";

export interface IMovementReport {
  cliente: IClient;
  movimientos: IMovement[];
}
