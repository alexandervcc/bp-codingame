package acc.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente{
//public class Cliente extends Persona{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clienteId;
	
	@NotNull
	@NotBlank(message = "Contrasena es obligatoria")
	@Min(16)
	private String contrasena;
	
	@NotNull
	@NotBlank(message="Estado es Obligatorio")
	private Boolean estado;
}
