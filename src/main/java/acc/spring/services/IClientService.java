package acc.spring.services;

import java.util.List;

import acc.spring.DTO.ClientDto;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Client;

public interface IClientService {
    public Client createNewClient(ClientDto clienteDto) throws Exception;

    public Client updateClient(Long id, ClientDto clienteDto) throws NotFoundException;

    public void deleteClientById(Long id) throws NotFoundException;

    public List<Client> getAllClients();

    public Client getClientById(Long id) throws NotFoundException;

}
