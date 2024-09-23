package ru.diasoft.otusspring.shell.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.diasoft.otusspring.domain.Book;

@Component
public class BookConverter implements Converter<String, Book> {
    @Override
    public Book convert(String s) {
        String[] args = s.split(";");
        Book book = new Book();
        book.setId(Long.parseLong(args[0]));
        book.setName(args[1]);
        book.setDescription(args[2]);
        book.setAuthorId(Long.parseLong(args[3]));
        book.setGenreId(Long.parseLong(args[4]));

        return book;
    }
}
