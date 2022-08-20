package acc.spring.services;

import acc.spring.DTO.ClientDto;
import acc.spring.model.Person;

public interface IPersonService {
    public Person createNewPersona(ClientDto clienteDto);
}
