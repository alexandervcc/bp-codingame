package acc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 10, message = "Tamano invalido para nombre")
	@NotNull(message = "El nombre es obligatorio")
	private String nombre;

	@Size(min = 1, max = 1, message = "Tamano invalido de genero.")
	@Pattern(regexp = "M|F", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valor invalido para genero")
	@NotNull(message = "El genero es obligatorio")
	private String genero;

	@NotNull(message = "La edad es obligatorio.")
	@Min(18)
	@Max(100)
	private Integer edad;

	@NotNull(message = "La identificacion es obligatoria.")
	@Column(unique = true)
	@Pattern(regexp = "[\\d]{10}", message = "Valor invalido para identificacion.")
	private String identificacion;

	@NotNull(message = "La direccion es obligatorio.")
	@Size(min = 10, message = "Tamano de direccion debe ser de almenos 10 caracteres.")
	private String direccion;

	@NotNull(message = "El telefono es obligatorio.")
	@Size(min = 7, message = "Telefono invalido.")
	private String telefono;
}
