package ru.diasoft.otusspring.service;


import ru.diasoft.otusspring.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    void insert(Author author);

}
