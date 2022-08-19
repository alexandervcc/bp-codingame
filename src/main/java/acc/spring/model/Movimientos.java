package acc.spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "movimiento")
public class Movimientos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "Valor de fecha es obligatorio")
	private Timestamp fecha;
	
	@NotBlank(message = "El tipo de movimienot es obligatorio")
	@NotNull
	@Pattern(regexp = "DEBITO|CREDITO", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valor invalido para Tipo de movimiento")
	private String tipoMovimiento;

	@NotBlank(message = "El valor es obligatorio")
	@NotNull
	@Min(1)
	private Long valor;

	@NotBlank(message = "El saldo es obligatorio")
	@NotNull
	@Min(0)
	private Long saldo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
}
