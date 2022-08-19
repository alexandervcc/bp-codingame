package acc.spring.services;

import java.util.List;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Cliente;

public interface IClienteService {
    public Cliente createNewClient(ClienteDto clienteDto);
    public Cliente updateClient(Long id, ClienteDto clienteDto);
    public String deleteClientById(Long id);
    public List<Cliente> getAllClients();
    public Cliente getClientById(Long id);
    
}
