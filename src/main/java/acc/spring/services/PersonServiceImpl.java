package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.ClientDto;
import acc.spring.model.Person;
import acc.spring.repository.PersonRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class PersonServiceImpl implements IPersonService {
    private PersonRepository personaRepository;

    @Override
    public Person createNewPersona(ClientDto clienteDto) {
        Person nuevaPersona = Person.builder()
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
