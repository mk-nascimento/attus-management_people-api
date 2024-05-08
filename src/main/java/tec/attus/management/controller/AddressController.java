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
import tec.attus.management.model.Address;
import tec.attus.management.model.Person;
import tec.attus.management.service.AddressService;
import tec.attus.management.service.PersonService;
import tec.attus.management.utils.Utils;

@RestController
@RequestMapping("/api")
public class AddressController {
    private AddressService service;
    private PersonService personService;

    public AddressController(AddressService address, PersonService person) {
        this.service = address;
        this.personService = person;
    }

    @PostMapping("/person/{personId}/address")
    public ResponseEntity<Object> create(@PathVariable Long personId, @RequestBody Address entity) {
        Optional<Person> instance = personService.readUnique(personId);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()));

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(instance.get(), entity));
    }

    @GetMapping("/person/{personId}/address")
    public ResponseEntity<List<Address>> read(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.read(personId));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Object> readUnique(@PathVariable Long id) {
        Optional<Address> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()));

        return ResponseEntity.ok().body(instance.get());
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Address entity) {
        Optional<Address> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()));

        Utils.copyNonNull(entity, instance.get());

        return ResponseEntity.ok().body(service.update(instance.get()));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Address> instance = service.readUnique(id);

        if (Boolean.FALSE.equals(instance.isPresent()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()));

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
