package acc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Movimientos;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos,Long> {
  
}
