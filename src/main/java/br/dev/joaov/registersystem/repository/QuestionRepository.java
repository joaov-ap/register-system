package br.dev.joaov.registersystem.repository;

import br.dev.joaov.registersystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
