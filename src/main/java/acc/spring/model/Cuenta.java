package acc.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "cuenta")
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroDeCuenta;
	
	@NotBlank(message = "El tipo de cuenta es obligatorio")
	@Size(min = 1,max = 1)
	@Pattern(regexp = "AHORROS|CORRIENTE", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valor invalido para Tipo de cuenta")
	@NotNull
	private String tipoDeCuenta;

	@NotBlank(message = "El saldo inicial es obligatorio")
	@Size(min = 0)
	@NotNull	
	private Long saldoInicial;

	@NotBlank(message = "El estado es obligatorio")
	@NotNull	
	private Boolean estado;
}
