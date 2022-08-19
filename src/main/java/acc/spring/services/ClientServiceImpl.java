package acc.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Client;
import acc.spring.repository.ClientRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
  private ClientRepository clienteRepository;

  @Override
  public Client createNewClient(ClienteDto clientedto) {
    Client nuevoCliente = Client.builder()
        .clienteId(null)
        .contrasena(clientedto.contrasena)
        .estado(clientedto.estado)
        .build();
    clienteRepository.save(nuevoCliente);
    return nuevoCliente;
  }

  @Override
  public String deleteClientById(Long id) {
    clienteRepository.deleteById(id);
    return "Deleted";
  }

  @Override
  public List<Client> getAllClients() {
    List<Client> listaClientes = clienteRepository.findAll();
    return listaClientes;
  }

  @Override
  public Client getClientById(Long id) {
    Client cliente = clienteRepository.findById(id).orElseThrow();
    return cliente;
  }

  @Override
  public Client updateClient(Long id, ClienteDto clienteDto) {
    Client cliente = clienteRepository.findById(id).orElseThrow();
    if(clienteDto.contrasena!=null)cliente.setContrasena(clienteDto.contrasena);
    if(clienteDto.estado!=null)cliente.setEstado(clienteDto.estado);
    clienteRepository.save(cliente);
    return cliente;
  }

}
