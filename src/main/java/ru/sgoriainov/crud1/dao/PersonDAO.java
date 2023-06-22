package ru.sgoriainov.crud1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sgoriainov.crud1.models.Book;
import ru.sgoriainov.crud1.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component

public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional (readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        List <Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;

    }

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        person.setAge(updatedPerson.getAge());
        person.setFullName(updatedPerson.getFullName());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }

    public Optional<Person> getPersonByFullName(String fullName) {
     return null;
    }

    public List<Book> getBooksByPersonId(int id) {
        return null;
    }
}

