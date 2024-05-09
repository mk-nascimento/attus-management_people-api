package tec.attus.management.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    void createAddressWithValidParameters() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("Main St");
        address.setNumber("123");
        address.setCity("New York");
        address.setState("NY");
        address.setPostalCode("12345-678");
        address.setMain(false);

        Person person = new Person();
        person.setId(1L);
        person.getAddresses().add(address);
        address.setPerson(person);

        Assertions.assertEquals(1L, address.getId());
        Assertions.assertEquals("Main St", address.getStreet());
        Assertions.assertEquals("123", address.getNumber());
        Assertions.assertEquals("New York", address.getCity());
        Assertions.assertEquals("NY", address.getState());
        Assertions.assertEquals("12345-678", address.getPostalCode());
        Assertions.assertFalse(address.getMain());
        Assertions.assertEquals(person, address.getPerson());
        Assertions.assertEquals(address, person.getAddresses().get(0));
    }
}
