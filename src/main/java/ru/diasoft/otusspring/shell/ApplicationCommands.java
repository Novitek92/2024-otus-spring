package ru.diasoft.otusspring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.diasoft.otusspring.domain.Author;
import ru.diasoft.otusspring.domain.Book;
import ru.diasoft.otusspring.domain.Genre;
import ru.diasoft.otusspring.service.AuthorService;
import ru.diasoft.otusspring.service.BookService;
import ru.diasoft.otusspring.service.GenreService;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Count books", key = {"cb", "count_books"})
    public Integer countBooks() {
        return bookService.count();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findBookById(Long id) throws Exception {
        try {
            return bookService.findById(id);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Insert book. Example: \"2;Anna Karenina;A novel about the tragic love of a married lady and a brilliant officer;1;1\"", key = {"ib", "insert_book"})
    public Book insertBook(Book book) {
        return bookService.insert(book);
    }

    @ShellMethod(value = "Update book by id. Eexample: \"2;Anna Karenina;bla bla;1;1\"", key = {"ub", "update_book"})
    public Book updateBookById(Book book) throws Exception {
        try {
            return bookService.updateById(book);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + book.getId());
        }
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteBookById(Long id) throws Exception {
        try {
            bookService.deleteById(id);
            System.out.println("Book with id = " + id +" successfully deleted");
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
    public List<Genre> findAllGenres() {
        return genreService.findAll();
    }

    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }
}
