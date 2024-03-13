package ru.diasoft.otusspring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.diasoft.otusspring.dao.QuestionDao;
import ru.diasoft.otusspring.dao.QuestionDaoImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
@DisplayName("класс QuestionServiceImpl")
class QuestionServiceImplTest {
    @DisplayName("должен выбросить ошибку, если не указано имя файла")
    @Test
    void testingStudent() {
        QuestionDao dao = new QuestionDaoImpl();

        assertThrows(IllegalArgumentException.class, dao::getQuestions);
    }
}