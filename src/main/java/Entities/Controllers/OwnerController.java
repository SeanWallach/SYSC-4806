package Entities.Controllers;

import Entities.Book;
import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OwnerController {
    @Autowired
    private BookRepo books;

    @Autowired
    private OwnerRepo owners;


    //-----------------------------------Owner creation------------------------------------------//
    //adds owner to model and sends to ownerCreation html page.
    @GetMapping("/createOwner")
    public String createOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "ownerCreation";
    }

    //
    @PostMapping("/createOwner")
    public String ownerSubmit(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password) {
        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owners.save(owner);

        return "loginPage";
    }



    //-------------------Methods to create and display books that owners make-------------------//
    @GetMapping("/createBook")
    public String createBook(Model model){
        model.addAttribute("newBook", new Book());
        return "bookCreation";
    }

    @PostMapping("/createBook")
    public String publishBook(@ModelAttribute Book book, Model model){
        books.save(book);
        model.addAttribute("createdBook", book);
        return "bookDisplay";
    }

}
