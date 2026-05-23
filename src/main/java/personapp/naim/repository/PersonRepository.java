package personapp.naim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import personapp.naim.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
    Person findTopByOrderByIdDesc();
  
}
