package ru.diasoft.otusspring.dao;

import ru.diasoft.otusspring.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

}
