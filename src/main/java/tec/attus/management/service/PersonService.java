package tec.attus.management.service;

import java.util.List;
import java.util.Optional;

import tec.attus.management.model.Person;

public interface PersonService {
    Person create(Person person);

    List<Person> read();

    Boolean exists(Long id);

    Optional<Person> readUnique(Long id);

    void delete(Long id);
}
