package acc.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 10,max = 35)
	@NotNull
	private String nombre;

	@NotBlank(message = "El genero es obligatorio")
	@Size(min = 1,max = 1)
	@Pattern(regexp = "M|F", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valor invalido para genero")
	@NotNull
	private String genero;
	
	@NotBlank(message = "La edad es obligatorio")
	@NotNull
	@Min(18)
	@Max(100)
	private Integer edad;
	
	@NotBlank(message = "La identificacion es obligatorio")
	@NotNull
	@Pattern(regexp = "\\d", message = "Valor invalido para identificacion")//TODO:validar_cedula
	private String identification;
	
	@NotBlank(message = "La direccion es obligatorio")
	@NotNull
	private String direccion;
	
	@NotBlank(message = "El telefono es obligatorio")
	@NotNull	
	private String telefono;
}
