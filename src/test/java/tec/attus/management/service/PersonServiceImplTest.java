package tec.attus.management.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tec.attus.management.model.Person;
import tec.attus.management.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServiceImpl service;

    @Test
    void testPersonServiceCreate() {
        Person entity = new Person();
        entity.setId(1L);
        entity.setFullName("John Doe");
        entity.setBirthDate(LocalDate.of(1991, 1, 1));

        Mockito.when(repository.save(entity)).thenReturn(entity);
        Person instance = service.create(entity);

        Assertions.assertNotNull(instance.getId());
        Assertions.assertEquals(entity.getFullName(), instance.getFullName());
        Assertions.assertEquals(entity.getBirthDate(), instance.getBirthDate());

        Mockito.verify(repository).save(entity);
    }

    @Test
    void testPersonServiceRead() {
        Person entity1 = new Person();
        Person entity2 = new Person();

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));
        Assertions.assertEquals(Arrays.asList(entity1, entity2), service.read());

        Mockito.verify(repository).findAll();
    }

    @Test
    void testPersonServiceReadUnique() {
        Person entity = new Person();
        entity.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.existsById(1L)).thenReturn(true);

        Assertions.assertTrue(service.exists(1L));
        Assertions.assertTrue(service.readUnique(1L).isPresent());
        Assertions.assertEquals(entity, service.readUnique(1L).get());

        Mockito.verify(repository, Mockito.times(2)).findById(1L);
    }

    @Test
    void testPersonServiceDelete() {
        service.delete(1L);
        Mockito.verify(repository).deleteById(1L);
    }
}
