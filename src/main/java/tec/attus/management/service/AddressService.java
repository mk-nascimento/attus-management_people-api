package tec.attus.management.service;

import java.util.List;
import java.util.Optional;

import tec.attus.management.model.Address;
import tec.attus.management.model.Person;

public interface AddressService {
    Address create(Person person, Address address);

    Address update(Address address);

    List<Address> read(Long personId);

    Boolean exists(Long id);

    Optional<Address> readUnique(Long id);

    void delete(Long id);
}
