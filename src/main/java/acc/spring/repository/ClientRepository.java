package acc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acc.spring.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
    @Query(value = "SELECT c FROM Client c WHERE c.nombre LIKE CONCAT('%',:nombreCliente,'%')")
    public List<Client> findUsersWithPartOfName(@Param("nombreCliente") String nombreCliente);
}
