package ru.diasoft.otusspring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import ru.diasoft.otusspring.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должен")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final Long EXPECTED_BOOK_ID = 2L;
    private static final String EXPECTED_BOOK_NAME = "Anna Karenina";
    private static final String EXPECTED_BOOK_DESCRIPTION = "A novel about the tragic love of a married lady and a brilliant officer";
    private static final Long EXPECTED_BOOK_AUTHOR_ID = 1L;
    private static final Long EXPECTED_BOOK_GENRE_ID = 1L;
    private static final Long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_NAME = "War and Peace";
    private static final String EXISTING_BOOK_DESCRIPTION = "An epic novel describing Russian society during the era of the wars against Napoleon in 1805-1812. The epilogue of the novel brings the narrative up to 1820";
    private static final Long EXISTING_BOOK_AUTHOR_ID = 1L;
    private static final Long EXISTING_BOOK_GENRE_ID = 1L;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Commit
    @DisplayName("добавлять книгу в БД")
    @Test
    void insert() {
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_BOOK_DESCRIPTION, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID);
        bookDaoJdbc.insert(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void count() {
        System.out.println(bookDaoJdbc.getAll());
        int actualBooksCount = bookDaoJdbc.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("возвращать ожидаемую книгу по ее id")
    @Test
    void getById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        Book actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAll() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("обновлять заданную книгу по ее id")
    @Test
    void update() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXPECTED_BOOK_NAME, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        bookDaoJdbc.update(expectedBook);
        Book actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Commit
    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void deleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(EXPECTED_BOOK_ID))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(EXPECTED_BOOK_ID);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXPECTED_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}