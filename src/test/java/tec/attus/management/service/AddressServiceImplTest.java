package tec.attus.management.service;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tec.attus.management.model.Address;
import tec.attus.management.model.Person;
import tec.attus.management.repository.AddressRepository;
import tec.attus.management.utils.Utils;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressRepository repository;

    @InjectMocks
    private AddressServiceImpl service;

    @Test
    void testAddressServiceCreate() {
        Address entity = new Address();
        Person person = new Person();
        entity.setId(1L);
        entity.setStreet("Groove Street");
        entity.setNumber("1");
        entity.setCity("Los Santos");
        entity.setState("LS");
        entity.setPostalCode("12345-67");
        entity.setMain(true);
        entity.setPerson(person);

        Mockito.when(repository.save(entity)).thenReturn(entity);
        Address instance = service.create(person, entity);

        Assertions.assertNotNull(instance.getId());
        Assertions.assertEquals(entity.getPerson(), instance.getPerson());
        Assertions.assertEquals(entity.getStreet(), instance.getStreet());
        Assertions.assertEquals(entity.getNumber(), instance.getNumber());
        Assertions.assertEquals(entity.getCity(), instance.getCity());
        Assertions.assertEquals(entity.getState(), instance.getState());
        Assertions.assertEquals(entity.getPostalCode(), instance.getPostalCode());
        Assertions.assertTrue(instance.getMain());

        Mockito.verify(repository).save(entity);
    }

    @Test
    void testAddressServiceRead() {
        Address entity1 = new Address();
        Address entity2 = new Address();
        Person person = new Person();
        person.setId(1L);
        entity1.setPerson(person);
        entity2.setPerson(person);

        Mockito.when(repository.findByPersonId(1L)).thenReturn(Arrays.asList(entity1, entity2));
        Assertions.assertEquals(Arrays.asList(entity1, entity2), service.read(1L));

        Mockito.verify(repository).findByPersonId(1L);
    }

    @Test
    void testAddressServiceReadUnique() {
        Address entity = new Address();
        entity.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.existsById(1L)).thenReturn(true);

        Assertions.assertTrue(service.exists(1L));
        Assertions.assertTrue(service.readUnique(1L).isPresent());
        Assertions.assertEquals(entity, service.readUnique(1L).get());

        Mockito.verify(repository, Mockito.times(2)).findById(1L);
    }

    @Test
    void testAddressServiceUpdate() {
        Address entity1 = new Address();
        Address entity2 = new Address();
        entity1.setId(1L);
        entity2.setId(1L);
        entity2.setPostalCode("12345-678");

        Mockito.when(repository.save(entity1)).thenReturn(entity1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity1));
        service.create(new Person(), entity1);

        Optional<Address> instance = service.readUnique(1L);
        Utils.copyNonNull(entity2, instance.get());
        service.update(instance.get());

        Assertions.assertTrue(instance.isPresent());
        Assertions.assertNotNull(instance.get().getPostalCode());

        Mockito.verify(repository).findById(1L);
        Mockito.verify(repository, Mockito.times(2)).save(entity1);
    }

    @Test
    void testAddressServiceDelete() {
        service.delete(1L);
        Mockito.verify(repository).deleteById(1L);
    }
}
