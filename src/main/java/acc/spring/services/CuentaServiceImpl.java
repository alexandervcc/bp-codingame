package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.CuentaRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CuentaServiceImpl {
  private CuentaRepository cuentaRepository;
}
