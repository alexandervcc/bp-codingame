package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.MovimientoRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MovimientoServiceImpl {
  private MovimientoRepository movimientoRepository;
}
