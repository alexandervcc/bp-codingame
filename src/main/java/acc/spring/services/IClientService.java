package acc.spring.services;

import java.util.List;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Client;

public interface IClientService {
    public Client createNewClient(ClienteDto clienteDto);
    public Client updateClient(Long id, ClienteDto clienteDto);
    public String deleteClientById(Long id);
    public List<Client> getAllClients();
    public Client getClientById(Long id);
    
}
