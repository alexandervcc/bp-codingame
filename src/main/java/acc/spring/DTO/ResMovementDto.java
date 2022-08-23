package acc.spring.DTO;

import java.sql.Timestamp;

public class ResMovementDto {
	public Long id;
	public Timestamp fecha;

	public String tipoMovimiento;
	public Long valor;
	public Long saldoInicial;
	public Long saldoDisponible;

	public String tipoCuentaOrigen;
	public Long cuentaOrigen;

	public Boolean estado;

	public String cliente;
}
