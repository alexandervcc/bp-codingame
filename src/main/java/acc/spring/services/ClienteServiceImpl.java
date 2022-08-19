package acc.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Cliente;
import acc.spring.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClienteServiceImpl implements IClienteService {
  private ClienteRepository clienteRepository;

  @Override
  public Cliente createNewClient(ClienteDto clientedto) {
    Cliente nuevoCliente = Cliente.builder()
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
  public List<Cliente> getAllClients() {
    List<Cliente> listaClientes = clienteRepository.findAll();
    return listaClientes;
  }

  @Override
  public Cliente getClientById(Long id) {
    Cliente cliente = clienteRepository.findById(id).orElseThrow();
    return cliente;
  }

  @Override
  public Cliente updateClient(Long id, ClienteDto clienteDto) {
    Cliente cliente = clienteRepository.findById(id).orElseThrow();
    if(clienteDto.contrasena!=null)cliente.setContrasena(clienteDto.contrasena);
    if(clienteDto.estado!=null)cliente.setEstado(clienteDto.estado);
    clienteRepository.save(cliente);
    return cliente;
  }

}
