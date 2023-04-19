package ru.sgoriainov.crud1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sgoriainov.crud1.dao.BookDAO;
import ru.sgoriainov.crud1.dao.PersonDAO;
import ru.sgoriainov.crud1.models.Book;
import ru.sgoriainov.crud1.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model) {
        model.addAttribute("book", bookDAO.show(book_id));

        return "books/show";
    }

    @GetMapping("/new")
    public String newbook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookDAO.show(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("book_id") int book_id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(book_id, book);
        return "redirect:/books";
    }
//добавляем
    @PatchMapping("/{book_id}/add")
    public String addHolder(@ModelAttribute("person")Person person,Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("people", personDAO.index());
        bookDAO.addFK(person.getId(), book_id);
        return "books/add";
    }


    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
        bookDAO.delete(book_id);
        return "redirect:/books";
    }
}