package acc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
  
}
