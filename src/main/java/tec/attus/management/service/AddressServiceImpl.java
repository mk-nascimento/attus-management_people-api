package tec.attus.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tec.attus.management.model.Address;
import tec.attus.management.model.Person;
import tec.attus.management.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address create(Person person, Address address) {
        address.setPerson(person);
        return repository.save(address);
    }

    @Override
    public Address update(Address address) {
        return repository.save(address);
    }

    @Override
    public List<Address> read(Long personId) {
        return repository.findByPersonId(personId);
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Address> readUnique(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
