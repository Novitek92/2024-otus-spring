package ru.diasoft.otusspring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;;

@SpringBootTest
@DisplayName("класс QuestionDaoImplTest")
class QuestionDaoImplTest {

    @Autowired
    private QuestionDaoImpl questionDao;

    @DisplayName("должен вернуть список вопросов")
    @Test
    void getQuestions() {
        assertThat(questionDao.getQuestions()).isNotNull();
    }

}