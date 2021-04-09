package Entities.Controllers;



import Entities.Book;
import Entities.Cart;
import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.ClientRepo;
import Entities.Client;
import Entities.Repositories.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IStandaloneElementTag;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private ClientRepo users;

    @Autowired

    private BookRepo books;
    
    @Autowired
    private OwnerRepo owners;


    //-----------------------------------User creation------------------------------------------//
    //adds user to model and sends to userCreation html page.
    @GetMapping("/createUser")
    public String createUser(Model model) {
        return "userCreation";
    }

    //
    @PostMapping("/createUser")
    public String ownerSubmit(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password) {

        Client user = new Client();
        user.setUsername(username);
        user.setPassword(password);
        users.save(user);

        return "loginPage";
    }

    @RequestMapping("/filter")
    public String filter(Model model, @RequestParam(value="keyword") String keyword) {
        ArrayList<Book> library = new ArrayList<Book>();

        for (Book book: books.findAll()) {
            // This method compiles keyword, then tries to match it to every (useful) attribute of Book
            boolean b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getName()).find();
            b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getAuthor()).find();
            b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getPublisher()).find();
            b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getDescription()).find();
            b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getName()).find();
            b = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getName()).find();

            // If any of the above is a match, add the book to the library
            if (b) {
                System.out.println("added " + book.getName() + " to the search results");
                library.add(book);
            }
            model.addAttribute("library",library);


        }
        return "userHomepage";
    }



}
