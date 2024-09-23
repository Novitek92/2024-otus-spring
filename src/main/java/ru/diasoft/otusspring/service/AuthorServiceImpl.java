package ru.diasoft.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.AuthorDao;
import ru.diasoft.otusspring.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.getAll();
    }

    @Override
    public void insert(Author author) {
        authorDao.insert(author);
    }

}
