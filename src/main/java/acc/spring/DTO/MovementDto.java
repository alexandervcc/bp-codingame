package acc.spring.DTO;

import java.sql.Timestamp;

public class MovementDto {
	public Long movementId;
	public Timestamp fecha;
	public String tipoMovimiento;
	public Long valor;
	public Long saldo;
	public Long cuentaId;
}
