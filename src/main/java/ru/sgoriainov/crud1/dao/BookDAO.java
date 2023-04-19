package ru.sgoriainov.crud1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sgoriainov.crud1.models.Book;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, writer, age) VALUES(?, ?, ?)", book.getName(), book.getWriter(), book.getAge());
    }

    public void update(int book_id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, writer=?, age=? WHERE book_id=?", updatedBook.getName(), updatedBook.getWriter(),
                updatedBook.getAge(), book_id);
    }

    public void addFK(int id, int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", id, book_id);
    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }
}

