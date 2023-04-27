package ru.sgoriainov.crud1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sgoriainov.crud1.dao.PersonDAO;
import ru.sgoriainov.crud1.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName","","person with such a full name already exists");
    }
}
