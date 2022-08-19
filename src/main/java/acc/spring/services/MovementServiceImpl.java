package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.MovementRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MovementServiceImpl {
  private MovementRepository movimientoRepository;
}
