package ru.diasoft.otusspring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.otusspring.dao.QuestionDao;
import ru.diasoft.otusspring.dao.QuestionDaoImpl;
import ru.diasoft.otusspring.domain.Question;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("класс QuestionServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @DisplayName("должен тестировать студента")
    @Test
    void testingStudent() {
        provideInput("vvolkova\n2\n");
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("1+1?", "2"));

        when(questionDao.getQuestions(any())).thenReturn(questions);
        assertAll(questionService::testingStudent);

    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}