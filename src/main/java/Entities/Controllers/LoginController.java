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

@Controller
public class LoginController {
    @Autowired
    private BookRepo books;

    @Autowired
    private OwnerRepo owners;


    //----------------------------------------Login page------------------------------------//
    @RequestMapping("/")
    public String login(){
        return "loginPage";
    }

    //--------------------------------------Validate Login--------------------------------------//
    //checks if owner is in owner repo, if yes -> sends to owner screen, if no -> sends back to login page
    @GetMapping("/validateLogin")
    public String validateLogin(@ModelAttribute Owner owner, Model model) {

        for(Owner o: owners.findAll()){
            if(o.getId() == owner.getId()) return "ownerHomepage";
        }
        return "loginPage";
    }

}
