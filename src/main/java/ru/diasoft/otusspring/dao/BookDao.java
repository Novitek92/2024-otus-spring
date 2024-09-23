package ru.diasoft.otusspring.dao;

import ru.diasoft.otusspring.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    void insert(Book book);

    Book getById(long id);
    List<Book> getAll();

    Long update(Book book);

    void deleteById(long id);

}
