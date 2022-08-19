package acc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Movements;

@Repository
public interface MovementRepository extends JpaRepository<Movements,Long> {
  
}
