package acc.spring.services;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Person;

public interface IPersonService {
    public Person createNewPersona(ClienteDto clienteDto);
}
