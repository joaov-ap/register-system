package br.dev.joaov.registersystem.dto;

import java.util.List;

public record PersonDto(String name, String email, int age, double height, List<String> answers) {
}
