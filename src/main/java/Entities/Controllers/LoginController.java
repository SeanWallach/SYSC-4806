package Entities.Controllers;

import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.OwnerRepo;
import Entities.Repositories.UserRepo;
import Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private BookRepo books;

    @Autowired
    private OwnerRepo owners;

    @Autowired
    UserRepo users;


    //----------------------------------------Login page------------------------------------//
    @RequestMapping("/")
    public String login(Model model){
        model.addAttribute("loginAttempt", new Owner());
        return "loginPage";
    }

    //--------------------------------------Validate Login--------------------------------------//
    //checks if owner is in owner repo, if yes -> sends to owner screen, if no -> sends back to login page
    @RequestMapping("/validateLogin")
    public String validateLogin(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                Model model) {

        Owner ownerAttempt = owners.findByUsername(username);
        User userAttempt = users.findByUsername(username);

        if(ownerAttempt != null) {
            if(ownerAttempt.getUsername().equals(username) && ownerAttempt.getPassword().equals(password)) {
                return "ownerHomepage";
            }
        }
        else if( userAttempt != null){
            if(userAttempt.getUsername().equals(username) && userAttempt.getPassword().equals(password)) {
                return "userHomepage";
            }
        }
        return "loginPage";
    }

}
