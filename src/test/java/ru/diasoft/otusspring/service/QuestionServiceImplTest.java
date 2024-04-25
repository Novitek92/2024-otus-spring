package ru.diasoft.otusspring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("класс QuestionServiceImpl")
class QuestionServiceImplTest {
    @Autowired
    private QuestionServiceImpl questionService;

    @DisplayName("должен тестировать студента")
    @Test
    void testingStudent() {
        String data = "Виктория Волкова\n2\n6\n9\n100\n1";
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);

        assertThat(questionService.testingStudent()).isTrue();

    }

}