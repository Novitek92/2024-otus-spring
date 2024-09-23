package ru.diasoft.otusspring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.diasoft.otusspring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        Integer count = jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, name, description, authorid, genreid) values (:id, :name, :description, :authorid, :genreid)",
                Map.of("id", book.getId(),"name", book.getName(), "description", book.getDescription(), "authorid", book.getAuthorId(), "genreid", book.getGenreId()));
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select * from books where id = :id", Map.of("id", id),
                new BookMapper());
    }
    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books",
                new BookMapper());
    }

    @Override
    public Long update(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValues(Map.of("id", book.getId(), "name", book.getName(), "description", book.getDescription(), "authorid", book.getAuthorId(), "genreid", book.getGenreId()));

        jdbc.update("update books set name = :name, description = :description, authorid = :authorid, genreid = :genreid where id = :id",
                parameterSource,
                keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            long authorId = resultSet.getLong("authorid");
            long genreId = resultSet.getLong("genreid");
            return new Book(id, name, description, authorId, genreId);
        }
    }
}
