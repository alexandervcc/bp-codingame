package acc.spring.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Account;
import acc.spring.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByCuentaOrderByFechaDesc(Account cuenta);
    
    
    List<Movement> findAllByCuentaAndFechaBetweenOrderByFechaDesc(Account cuenta, Timestamp fechaInicio, Timestamp fechaFin);
}
