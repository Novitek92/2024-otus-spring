package ru.diasoft.otusspring.dao;

import ru.diasoft.otusspring.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
