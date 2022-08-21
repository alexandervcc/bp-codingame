package acc.spring.DTO;

import java.sql.Timestamp;

public class MovementDto {
	public Long movementId;

	public Timestamp fechaInicio;
	public Timestamp fechaFin;

	public String tipoMovimiento;
	public Long valor;

	public Long cuentaOrigen;
	
	public String tipoCuentaOrigen;

}
