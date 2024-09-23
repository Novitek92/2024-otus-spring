package ru.diasoft.otusspring.shell.converter;

import org.junit.jupiter.api.Test;
import ru.diasoft.otusspring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

class BookConverterTest {

    @Test
    void convert() {
        BookConverter converter = new BookConverter();
        String string = "2;Anna Karenina;A novel about the tragic love of a married lady and a brilliant officer;1;1";
        Book book = new Book(2L,"Anna Karenina", "A novel about the tragic love of a married lady and a brilliant officer", 1L, 1L);

        assertThat(converter.convert(string))
                .isNotNull()
                .isInstanceOf(Book.class)
                .isEqualTo(book);
    }
}