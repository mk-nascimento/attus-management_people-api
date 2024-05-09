package tec.attus.management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void createPersonWithValidParameters() {
        Person person = new Person();
        person.setId(1L);
        person.setFullName("John Doe");
        person.setBirthDate(LocalDate.of(1990, 1, 1));

        Assertions.assertEquals(1L, person.getId());
        Assertions.assertEquals("John Doe", person.getFullName());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), person.getBirthDate());
    }

    @Test
    void addNewAddressToList() {
        Person person = new Person();
        person.setId(1L);
        person.setFullName("John Doe");
        person.setBirthDate(LocalDate.of(1990, 1, 1));

        Address address = new Address();
        address.setId(1L);
        address.setCity("New York");
        address.setMain(false);
        address.setNumber("123");
        address.setPerson(person);
        address.setState("NY");
        address.setStreet("Main St");

        person.getAddresses().add(address);

        Assertions.assertEquals(1, person.getAddresses().size());
        Assertions.assertEquals(address, person.getAddresses().get(0));
    }

    @Test
    void retrievePersonAddresses() {
        Person person = new Person();
        person.setId(1L);
        person.setFullName("John Doe");
        person.setBirthDate(LocalDate.of(1990, 1, 1));

        List<Address> addresses = new ArrayList<>();
        Address addr1 = new Address();
        addr1.setId(1L);
        addr1.setStreet("Main St");
        addr1.setNumber("123");
        addr1.setCity("New York");
        addr1.setState("NY");
        addr1.setMain(false);
        addr1.setPerson(person);

        Address addr2 = new Address();
        addr2.setId(2L);
        addr2.setStreet("Elm St");
        addr2.setNumber("456");
        addr2.setCity("Los Angeles");
        addr2.setState("CA");
        addr2.setMain(true);
        addr2.setPerson(person);
        addresses.add(addr2);

        person.setAddresses(addresses);

        List<Address> retrievedAddresses = person.getAddresses();

        Assertions.assertEquals(addresses, retrievedAddresses);
    }

    @Test
    void retrieveEmptyAddressList() {
        Person person = new Person();
        List<Address> addresses = person.getAddresses();
        Assertions.assertNotNull(addresses);
        Assertions.assertTrue(addresses.isEmpty());
    }
}
