package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClienteServiceImpl implements IClienteService {
  private ClienteRepository clienteRepository;
}
