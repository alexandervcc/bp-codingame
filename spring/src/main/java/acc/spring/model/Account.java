package acc.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull; 

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "cuenta")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroDeCuenta;

	@NotBlank(message = "El tipo de cuenta es obligatorio")
	@NotNull
	
	private String tipoDeCuenta;

	@Min(0)
	@NotNull(message = "El saldo inicial es obligatorio")
	private Long saldoInicial;

	@NotNull(message = "El estado es obligatorio")
	private Boolean estado;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnore
    private Client cliente;

	@OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Movement> movimientos;
}
