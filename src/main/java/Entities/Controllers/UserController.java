package Entities.Controllers;



import Entities.Owner;
import Entities.Repositories.UserRepo;
import Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepo users;

    //-----------------------------------User creation------------------------------------------//
    //adds user to model and sends to userCreation html page.
    @GetMapping("/createUser")
    public String createOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "userCreation";
    }

    //
    @PostMapping("/createUser")
    public String ownerSubmit(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        users.save(user);

        return "loginPage";
    }


}
