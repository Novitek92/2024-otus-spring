package ru.diasoft.otusspring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import ru.diasoft.otusspring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должен")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final Long EXPECTED_GENRE_ID = 2L;
    private static final String EXPECTED_GENRE_NAME = "romance";
    private static final Long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "fiction";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Commit
    @DisplayName("добавлять жанр в БД")
    @Test
    void insert() {
        Genre expectedGenre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);
        genreDaoJdbc.insert(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void count() {
        int actualAuthorsCount = genreDaoJdbc.count();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void getById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void getAll() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedGenre);
    }

    @Commit
    @DisplayName("удалять заданный жанр по его id")
    @Test
    void deleteById() {
        assertThatCode(() -> genreDaoJdbc.getById(EXPECTED_GENRE_ID))
                .doesNotThrowAnyException();

        genreDaoJdbc.deleteById(EXPECTED_GENRE_ID);

        assertThatThrownBy(() -> genreDaoJdbc.getById(EXPECTED_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}