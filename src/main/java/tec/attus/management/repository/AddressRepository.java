package tec.attus.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tec.attus.management.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByPersonId(Long personId);
}
