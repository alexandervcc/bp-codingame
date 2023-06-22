package acc.spring.DTO;

import java.sql.Timestamp;

public class MovementDto {
	public Long movementId;

	public Timestamp fechaInicio;
	public Timestamp fechaFin;
	public Timestamp fecha;

	public String tipoMovimiento;
	public Long valor;
	public Long saldoInicial;
	public Long saldoDisponible;

	public String tipoCuentaOrigen;
	public Long cuentaOrigen;
	

}
