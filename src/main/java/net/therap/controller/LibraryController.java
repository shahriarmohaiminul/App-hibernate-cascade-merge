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

        Book book = new Book();

        book.setTitle("The Book 1");

        Library library = new Library();

        library.setName("name1");
        library.setBook(book);
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
