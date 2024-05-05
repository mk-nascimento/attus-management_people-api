package tec.attus.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tec.attus.management.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
