package ru.diasoft.otusspring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.diasoft.otusspring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с авторами должен")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final Long EXPECTED_AUTHOR_ID = 2L;
    private static final String EXPECTED_AUTHOR_NAME = "Pushkin";
    private static final Long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void count() {
        int actualAuthorsCount = authorDaoJdbc.count();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void insert() {
        Author expectedAuthor = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        authorDaoJdbc.insert(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void getById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void getAll() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualAuthorList = authorDaoJdbc.getAll();
        assertThat(actualAuthorList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedAuthor);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void deleteById() {
        Author expectedAuthor = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        authorDaoJdbc.insert(expectedAuthor);

        assertThatCode(() -> authorDaoJdbc.getById(EXPECTED_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDaoJdbc.deleteById(EXPECTED_AUTHOR_ID);

        assertThatThrownBy(() -> authorDaoJdbc.getById(EXPECTED_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}