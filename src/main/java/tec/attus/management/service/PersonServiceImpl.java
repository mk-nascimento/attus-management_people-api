package tec.attus.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tec.attus.management.model.Person;
import tec.attus.management.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> read() {
        return repository.findAll();
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Person> readUnique(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
