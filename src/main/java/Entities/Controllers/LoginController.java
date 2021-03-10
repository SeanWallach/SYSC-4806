package Entities.Controllers;

import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.OwnerRepo;
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

        Owner attempt = owners.findByUsername(username);
        if(attempt != null) return "ownerHomepage";
        else return "loginPage";
    }

}
