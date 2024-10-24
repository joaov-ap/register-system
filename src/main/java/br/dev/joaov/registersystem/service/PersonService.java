package br.dev.joaov.registersystem.service;

import br.dev.joaov.registersystem.model.Person;
import br.dev.joaov.registersystem.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    @Transactional
    public Person save(Person person) {
        if (person.getName().length() < 10) {
            throw new RuntimeException("Name must be longer than 10 characters");
        }

        if (!person.getEmail().contains("@")) {
            throw new RuntimeException("Email must have '@'");
        }

        if (person.getAge() < 18) {
            throw new RuntimeException("Age must be greater than 18");
        }

        if (String.valueOf(person.getHeight()).contains(".")) {
            throw new RuntimeException("Height must have ',' instead of '.'");
        }

        return repository.save(person);
    }

    public List<Person> getPersons() {
        return repository.findAll();
    }

    @Transactional
    public Object updatePerson(Long id, Person person) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isEmpty()) {
            throw new RuntimeException("Person not found");
        }

        var person1 = personOptional.get();
        BeanUtils.copyProperties(person, person1);
        return repository.save(person1);
    }

    @Transactional
    public void deletePerson(Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isEmpty()) {
            throw new RuntimeException("Person not found");
        }
        repository.delete(person.get());
    }
}
