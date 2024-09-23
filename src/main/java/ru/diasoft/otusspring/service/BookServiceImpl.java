package ru.diasoft.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.BookDao;
import ru.diasoft.otusspring.domain.Book;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    public Book updateById(Book book) {
        Long id = bookDao.update(book);
        return findById(id);
    }

    @Override
    public Book findById(Long id) {
        Book book;
        try {
            book = bookDao.getById(id);
        } catch (Exception e) {
            return null;
        }
        return book;
    }

    @Override
    public void deleteById(Long id) {
        Book book = findById(id);
        if (book != null) {
            bookDao.deleteById(id);
        }
    }

    @Override
    public Book insert(Book book) {
        bookDao.insert(book);
        return findById(book.getId());
    }
}
