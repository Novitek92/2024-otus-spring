package ru.diasoft.otusspring.dao;


import ru.diasoft.otusspring.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    void insert(Genre author);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
