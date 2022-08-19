package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Persona;
import acc.spring.repository.PersonaRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class PersonaServiceImpl implements IPersonaService {
    private PersonaRepository personaRepository;

    @Override
    public Persona createNewPersona(ClienteDto clienteDto) {
        Persona nuevaPersona = Persona.builder()
                .id(null)
                .direccion(clienteDto.direccion)
                .edad(clienteDto.edad)
                .genero(clienteDto.genero)
                .identification(clienteDto.identification)
                .nombre(clienteDto.nombre)
                .telefono(clienteDto.telefono)
                .build();
        personaRepository.save(nuevaPersona);
        return nuevaPersona;
    }

}
