package ru.diasoft.otusspring.service;


import ru.diasoft.otusspring.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    void insert(Genre genre);

}
