package br.dev.joaov.registersystem.service;

import br.dev.joaov.registersystem.model.Question;
import br.dev.joaov.registersystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repository;

    @Transactional
    public Question save(Question question) {
        if (question.getQuestion().isEmpty()) {
            throw new RuntimeException("Question can't be blank");
        }
        return repository.save(question);
    }

    public List<Question> getQuestions() {
        return repository.findAll();
    }

    @Transactional
    public Object deleteQuestion(Long id) {
        if (id == 1 || id == 2 || id == 3 || id == 4) {
            throw new RuntimeException("You cannot delete questions with IDs 1, 2, 3 and 4");
        }

        Optional<Question> question = repository.findById(id);
        if (question.isEmpty()) {
            throw new RuntimeException("Question not found");
        }

        repository.delete(question.get());
        return "Question deleted successfully";
    }
}
