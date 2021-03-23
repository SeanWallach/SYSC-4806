package Entities.Controllers;

import Entities.Book;
import Entities.Cart;
import Entities.Client;
import Entities.Repositories.BookRepo;
import Entities.Repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired
    private BookRepo books;

    @Autowired
    private ClientRepo users;

    //----------------------------------------Add Book to Cart------------------------------------//
    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("bookID") Integer bookID,
                            @ModelAttribute("userID") Integer userID,
                            Model model){

        //Client client = users.findById(userID);
        //Book book = books.findById(bookID);

        /*client.getCart().addBook(book);

        users.save(client);

        model.addAttribute("Cart", client.getCart());


        model.addAttribute("library", library);*/



        return "userHomepage";


    }

}
