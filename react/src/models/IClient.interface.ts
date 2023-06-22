export interface IClient {
  id?: number;
  nombre: string;
  identificacion: string;
  direccion?: string;
  telefono?: string;
  edad?: number;
  genero?: string;
  contrasena?: string;
  estado?: boolean;
}
