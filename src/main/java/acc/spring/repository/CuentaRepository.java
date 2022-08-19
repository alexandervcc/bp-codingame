package acc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Cuenta;

@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta,Long>{
  
}
