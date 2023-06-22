export interface IMovement {
  id: number;
  fecha: Date;
  tipoMovimiento: string;
  valor: number;
  saldoInicial: number;
  saldoDisponible: number;
  tipoCuentaOrigen: string;
  cuentaOrigen: number;
  estado: boolean;
  cliente: string;
}
