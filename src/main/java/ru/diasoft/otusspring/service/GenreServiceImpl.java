package ru.diasoft.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.GenreDao;
import ru.diasoft.otusspring.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.getAll();
    }

    @Override
    public void insert(Genre genre) {
        genreDao.insert(genre);
    }

}
