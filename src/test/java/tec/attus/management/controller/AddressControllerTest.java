package tec.attus.management.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tec.attus.management.exception.ErrorResponse;
import tec.attus.management.model.Address;
import tec.attus.management.model.Person;
import tec.attus.management.service.AddressService;
import tec.attus.management.service.PersonService;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AddressControllerTest {
    private AddressService service = Mockito.mock(AddressService.class);
    private PersonService personService = Mockito.mock(PersonService.class);
    private AddressController controller = new AddressController(service, personService);

    @Test
    void testCreateAddress() {
        Address address = new Address();
        Person person = new Person();

        Mockito.when(personService.readUnique(1L)).thenReturn(Optional.of(person));
        Mockito.when(service.create(person, address)).thenReturn(address);

        ResponseEntity<Object> response = controller.create(1L, address);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(address, response.getBody());
    }

    @Test
    void testCreateAddressWithInvalidPerson() {
        Address address = new Address();
        Person person = new Person();

        Mockito.when(personService.readUnique(1L)).thenReturn(Optional.of(person));
        Mockito.when(service.create(person, address)).thenReturn(address);

        ResponseEntity<Object> response = controller.create(9999L, address);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()), response.getBody());
    }

    @Test
    void testReadAddresses() {
        List<Address> entities = Arrays.asList(new Address(), new Address());
        Mockito.when(service.read(1L)).thenReturn(entities);

        ResponseEntity<List<Address>> response = controller.read(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(entities, response.getBody());
    }

    @Test
    void testReadUniqueAddress() {
        Address entity = new Address();
        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(entity));

        ResponseEntity<Object> response = controller.readUnique(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(entity, response.getBody());
    }

    @Test
    void testUpdateAddress() {
        Address entity = new Address();
        Address address = new Address();

        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(entity));
        Mockito.when(service.update(entity)).thenReturn(address);

        ResponseEntity<Object> response = controller.update(1L, address);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(address, response.getBody());
    }

    @Test
    void testDeleteAddress() {
        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(new Address()));
        ResponseEntity<Object> response = controller.delete(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    void testReadInvalidUniqueAddress() {
        ResponseEntity<Object> response = controller.readUnique(999L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()), response.getBody());
    }

    @Test
    void testUpdateInvalidAddress() {
        ResponseEntity<Object> response = controller.update(999L, new Address());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
    }

    @Test
    void testDeleteInvalidAddress() {
        ResponseEntity<Object> response = controller.delete(999L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
    }
}
