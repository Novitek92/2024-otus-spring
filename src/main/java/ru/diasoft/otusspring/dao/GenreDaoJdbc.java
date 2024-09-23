package ru.diasoft.otusspring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.diasoft.otusspring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        Integer count = jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genres (id, name) values (:id, :name)",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from genres where id = :id",
                Map.of("id", id),
                new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres",
                new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from genres where id = :id", Map.of("id", id));
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id =resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
