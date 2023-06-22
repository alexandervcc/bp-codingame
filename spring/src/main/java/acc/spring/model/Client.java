package acc.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Client extends Person {

	@NotNull(message = "Contrasena es obligatoria")
	@Size(min = 8, message = "Tamano de contransena invalida.")
	private String contrasena;

	@NotNull(message = "Estado de cuenta es obligatorio")
	private Boolean estado;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Account> cuentas;
}
