package personapp.naim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import personapp.naim.model.Person;
import personapp.naim.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(String id) {
        personRepository.deleteById(id);
    }
    public Person findMostRecentPerson() {
        return personRepository.findTopByOrderByIdDesc();
    }
}
