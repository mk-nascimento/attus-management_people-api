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
import tec.attus.management.model.Person;
import tec.attus.management.service.PersonService;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PersonControllerTest {
    private PersonService service = Mockito.mock(PersonService.class);
    private PersonController controller = new PersonController(service);

    @Test
    void testCreateAndReturnsWith201() {
        Person entity = new Person();
        Mockito.when(service.create(entity)).thenReturn(entity);

        ResponseEntity<Person> response = controller.create(entity);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(entity, response.getBody());
    }

    @Test
    void testReadUniquePerson() {
        Person entity = new Person();
        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(entity));

        ResponseEntity<Object> response = controller.readUnique(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(entity, response.getBody());
    }

    @Test
    void testReadAll() {
        List<Person> entities = Arrays.asList(new Person(), new Person());
        Mockito.when(service.read()).thenReturn(entities);

        ResponseEntity<List<Person>> response = controller.read();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(entities, response.getBody());
    }

    @Test
    void testDeleteUnique() {
        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(new Person()));
        ResponseEntity<Object> response = controller.delete(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    void testUpdateUnique() {
        Person entity = new Person();
        Person person = new Person();

        Mockito.when(service.readUnique(1L)).thenReturn(Optional.of(entity));

        ResponseEntity<Object> response = controller.update(1L, person);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testReadInvalidPerson() {
        ResponseEntity<Object> response = controller.readUnique(9999L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
    }

    @Test
    void testDeleteInvalidPerson() {
        ResponseEntity<Object> response = controller.delete(9999L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
    }

    @Test
    void testUpdateInvalidPerson() {
        ResponseEntity<Object> response = controller.update(9999L, new Person());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
    }
}
