package acc.spring.services;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Persona;

public interface IPersonaService {
    public Persona createNewPersona(ClienteDto clienteDto);
}
