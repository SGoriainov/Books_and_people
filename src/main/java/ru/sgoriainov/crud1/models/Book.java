package ru.sgoriainov.crud1.models;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 30, message = "Book name size should be between 2 and 30 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Book author should not be empty")
    @Size(min = 2, max = 30, message = "Book author should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Year should be greater than 1500")
    @Column(name = "year")
    private int year;

    public Book () {}


    public Book (String title, String author, int year) {

        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
