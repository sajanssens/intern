package nl.infosupport.intern.recognition.repositories;

import nl.infosupport.intern.recognition.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

}
