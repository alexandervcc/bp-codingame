package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.AccountRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AccountServiceImpl {
  private AccountRepository cuentaRepository;
}
