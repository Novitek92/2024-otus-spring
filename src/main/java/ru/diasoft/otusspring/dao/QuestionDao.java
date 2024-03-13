package ru.diasoft.otusspring.dao;

import com.opencsv.exceptions.CsvValidationException;
import ru.diasoft.otusspring.domain.Question;

import java.io.IOException;
import java.util.Map;

public interface QuestionDao {
    Map<Question, String> getQuestions() throws CsvValidationException, IOException;
}
