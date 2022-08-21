package acc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
  List<Person> findByIdentificacion(String identificacion);
}
