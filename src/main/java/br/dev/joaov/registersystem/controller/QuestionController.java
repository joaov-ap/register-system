package br.dev.joaov.registersystem.controller;

import br.dev.joaov.registersystem.model.Question;
import br.dev.joaov.registersystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(question));
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getQuestions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteQuestion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
