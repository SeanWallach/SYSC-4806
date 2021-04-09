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
    public String filter(Model model,
                         @RequestParam(value="keyword") String keyword,
                         @RequestParam(value="userID") long userID) {

        ArrayList<Book> library = new ArrayList<Book>();            // where the search results are stored before being added to the model

        for (Book book: books.findAll()) {

            // non-string variables need to be converted to string for the matching algorithm
            String inventory = Integer.toString(book.getInventory());
            String ISBN = Long.toString(book.getISBN());
            String price = Double.toString(book.getPrice());

            // This for loop compiles the keyword, then tries to match it to every (useful) attribute of Book
            if(Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getName()).find()) {
                System.out.println("added " + book.getName() + " to the search results based on Book name");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getDescription()).find()) {
                System.out.println("added " + book.getName() + " to the search results based on Description");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getAuthor()).find()) {
                System.out.println("added " + book.getName() + " to the search results based on Author");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(book.getPublisher()).find()) {
                System.out.println("added " + book.getName() + " to the search results based on publisher");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(inventory).find()) {
                System.out.println("added " + book.getName() + " to the search results based on inventory");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(ISBN).find()) {
                System.out.println("added " + book.getName() + " to the search results based on ISBN");
                library.add(book);

            } else if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(price).find()) {
                System.out.println("added " + book.getName() + " to the search results based on price");
                library.add(book);
            }
        }
        ArrayList<Book> recommendationList = new ArrayList<Book>();
        Client user = users.findById(userID);
        for (Book b: books.findAll()) {
            if (user.checkAuthorHistory(b)){
                if (!recommendationList.contains(b)) {
                    recommendationList.add(b);
                }
            }

            if (!user.checkBookInHistory(b)) {
                if (!recommendationList.contains(b)) {
                    recommendationList.add(b);
                }
            }
        }

        model.addAttribute("userID",userID);
        model.addAttribute("library", library);
        model.addAttribute("recommendationList", recommendationList);
        System.out.println("Added search results to library");
        return "userHomepage";
    }

}
