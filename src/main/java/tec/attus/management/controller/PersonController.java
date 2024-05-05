package tec.attus.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tec.attus.management.exception.ErrorResponse;
import tec.attus.management.model.Person;
import tec.attus.management.service.PersonService;
import tec.attus.management.utils.Utils;

@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readUnique(@PathVariable Long id) {
        Optional<Person> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(String.format("No Person founded for: %s", id)));

        return ResponseEntity.ok().body(instance.get());
    }

    @GetMapping()
    public ResponseEntity<List<Person>> read() {
        return ResponseEntity.ok().body(service.read());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Person entity) {
        Optional<Person> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(String.format("Person #%s not found", id)));

        Utils.copyNonNull(entity, instance.get());

        return ResponseEntity.ok().body(service.create(instance.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Person> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(String.format("Person #%s not found", id)));

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
