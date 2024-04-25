package ru.diasoft.otusspring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Question {
    private final String question;
    private final String answer;
}
