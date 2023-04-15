package ru.sgoriainov.crud1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    //private int person_id;
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 30, message = "Book name size should be between 2 and 30 characters")
    private String name;
    private String writer;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    public Book () {}


    public Book (int id, String name, String writer, int age) {

        this.id = id;
        this.name = name;
        this.writer = writer;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
