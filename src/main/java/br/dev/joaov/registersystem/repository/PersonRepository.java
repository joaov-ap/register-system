package br.dev.joaov.registersystem.repository;

import br.dev.joaov.registersystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
