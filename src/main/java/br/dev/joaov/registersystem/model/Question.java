package br.dev.joaov.registersystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;
    private String question;
}
