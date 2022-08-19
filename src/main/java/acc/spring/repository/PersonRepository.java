package acc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.spring.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
  
}
