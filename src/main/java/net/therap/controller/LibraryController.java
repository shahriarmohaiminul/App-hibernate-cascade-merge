package net.therap.controller;

import net.therap.entity.Author;
import net.therap.entity.Book;
import net.therap.entity.Library;
import net.therap.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * @author shahriarmohaiminul
 * @since 9/7/24
 */
@Controller
@SessionAttributes(LibraryController.COMMAND_NAME)
@RequestMapping("/library/**")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    public static final String COMMAND_NAME = "library";

    @GetMapping("/list")
    public String getList(ModelMap model) {

        model.put("libraries", libraryService.getAllLibrary());

        return "list";
    }

    @GetMapping("/create")
    public String create(RedirectAttributes redirectAttributes) {

        Author author1 = new Author();
        Author author2 = new Author();

        Author author3 = new Author();
        Author author4 = new Author();

        author1.setName("John Doe");
        author2.setName("James Brown");


        author3.setName("Sam");
        author4.setName("Harry Potter");

        author1.setUpdated(new Date());
        author2.setUpdated(new Date());
        author3.setUpdated(new Date());
        author4.setUpdated(new Date());

        Book book1 = new Book();
        Book book2 = new Book();

        book1.setTitle("The Book 1");
        book2.setTitle("The Book 2");

        book1.getAuthors().add(author1);
        book1.getAuthors().add(author2);

        book2.getAuthors().add(author3);
        book2.getAuthors().add(author4);

        book1.setUpdated(new Date());
        book2.setUpdated(new Date());

        Library library = new Library();

        library.setName("name1");

        library.getBooks().add(book1);

        library.getBooks().add(book2);

        library.setUpdated(new Date());

        library = libraryService.save(library);

        redirectAttributes.addFlashAttribute("message", "Successfully added Library with id " + library.getId());

        return "redirect:/library/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, ModelMap model) {
        Library library = libraryService.findById(id);

        model.put("library", library);

        return "update";
    }

    @PostMapping(value = "/update", params = "_action_update")
    public String update(@ModelAttribute("library") Library library,
                         SessionStatus sessionStatus,
                         RedirectAttributes redirectAttributes) {

        library = libraryService.update(library);

        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("message", "Successfully Updated Library with id " + library.getId());

        return "redirect:/library/list";
    }
}
