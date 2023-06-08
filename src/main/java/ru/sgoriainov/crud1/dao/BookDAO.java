package ru.sgoriainov.crud1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sgoriainov.crud1.models.Book;
import ru.sgoriainov.crud1.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("select b from Book b", Book.class).getResultList();
        return books;
    }

    @Transactional
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        book.setAuthor(updatedBook.getAuthor());
        book.setTitle(updatedBook.getTitle());
        book.setYear(updatedBook.getYear());

        session.refresh(book);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class,id));
    }

    public Optional<Person> getBookOwner(int id) {
        return null;
        /*return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        int bookId = session.createQuery("select person_id from book WHERE book.id=:id",Book.class);
        Person person = session.get(Person.class,);*/
    }

    public void release(int id) {
        /*jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);*/
    }

    public void assign(int id, Person selectedPerson) {
        /*jdbcTemplate.update("UPDATE Book SET person_id= ? WHERE id=?",selectedPerson.getId(), id);*/
    }
}

