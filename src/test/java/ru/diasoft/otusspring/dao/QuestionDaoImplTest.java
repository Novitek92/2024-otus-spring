package ru.diasoft.otusspring.dao;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("класс QuestionDaoImplTest")
class QuestionDaoImplTest {
    @DisplayName("должен вернуть вопросы с ответом")
    @Test
    void getQuestions() {
        QuestionDaoImpl questionDao = new QuestionDaoImpl();
        assertNotNull(questionDao.getQuestions("/questions.csv"));
    }

    @DisplayName("должен выбросить ошибку, если не нашел файл или не смог прочитать")
    @Test
    void readCsvFileException() {
        QuestionDaoImpl questionDao = new QuestionDaoImpl();
        assertThrows(RuntimeException.class, () -> questionDao.getQuestions("/questions123.csv"));
    }

}