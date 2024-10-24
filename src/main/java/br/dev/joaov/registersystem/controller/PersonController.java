package br.dev.joaov.registersystem.controller;

import br.dev.joaov.registersystem.dto.PersonDto;
import br.dev.joaov.registersystem.model.Person;
import br.dev.joaov.registersystem.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody PersonDto personDto) {
        try {
            var person = new Person();
            BeanUtils.copyProperties(personDto, person);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value = "id") Long id, @RequestBody PersonDto personDto) {
        try {
            var person = new Person();
            BeanUtils.copyProperties(personDto, person);
            return ResponseEntity.status(HttpStatus.OK).body(service.updatePerson(id, person));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") Long id) {
        try {
            service.deletePerson(id);
            return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
