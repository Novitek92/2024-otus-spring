package ru.diasoft.otusspring.dao;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("класс QuestionDaoImplTest")
@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

    @Mock
    private QuestionDaoImpl questionDao;

    @DisplayName("должен вернуть мапу вопросов с ответом")
    @Test
    void getQuestions() throws CsvValidationException, IOException {
        assertThat(questionDao.getQuestions()).isNotNull();
    }

    @Test
    void readQuestion() {
        assertThat(questionDao.readQuestion(any())).isNotNull();
    }

    @DisplayName("должен считывать CSV")
    @Test
    void readCsvFile() throws CsvValidationException, IOException {
        assertNotNull(QuestionDaoImpl.readCsvFile("questions.csv"));
    }

    @DisplayName("должен выбросить ошибку, если не нашел файл")
    @Test
    void readCsvFileException() {
        assertThrows(IOException.class, () -> QuestionDaoImpl.readCsvFile("src/questions.csv"));
    }

}