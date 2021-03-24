package Entities.Controllers;

import Entities.Book;
import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.OwnerRepo;
import Entities.Repositories.ClientRepo;
import Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {
    @Autowired
    private BookRepo books;

    @Autowired
    private OwnerRepo owners;

    @Autowired
    ClientRepo users;


    //----------------------------------------Login page------------------------------------//
    @RequestMapping("/")
    public String login(Model model){
        return "loginPage";
    }

    //--------------------------------------Validate Login--------------------------------------//
    //checks if owner is in owner repo, if yes -> sends to owner screen, if no -> sends back to login page
    @RequestMapping("/validateLogin")
    public String validateLogin(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                Model model) {

        Owner ownerAttempt = owners.findByUsername(username);
        Client userAttempt = users.findByUsername(username);

        if(ownerAttempt != null) {
            if(ownerAttempt.getUsername().equals(username) && ownerAttempt.getPassword().equals(password)) {
                model.addAttribute("loggedInOwner", ownerAttempt);
                model.addAttribute("library", books.findAll());
                return "ownerHomepage";
            }
        }
        else if(userAttempt != null){
            if(userAttempt.getUsername().equals(username) && userAttempt.getPassword().equals(password)) {
                ArrayList<Book> library = new ArrayList<Book>();
                for (Book b : books.findAll()) {
                    library.add(b);
                    model.addAttribute("library", library);
                }
                return "userHomepage";
            }
        }
        return "loginPage";
    }
}
