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

import java.util.ArrayList;

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




}
