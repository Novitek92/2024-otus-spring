package ru.diasoft.otusspring.service;

import ru.diasoft.otusspring.domain.Book;

import java.util.List;

public interface BookService {

    int count();

    List<Book> findAll();

    Book updateById(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    Book insert(Book book);
}
