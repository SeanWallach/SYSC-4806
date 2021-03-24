package Entities.Controllers;

import Entities.Book;
import Entities.Cart;
import Entities.Client;
import Entities.Repositories.BookRepo;
import Entities.Repositories.CartRepo;
import Entities.Repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private CartRepo carts;

    @Autowired
    private BookRepo books;

    @Autowired
    private ClientRepo users;

    //----------------------------------------Add Book to Cart------------------------------------//
    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("bookID") Long bookID,
                            @ModelAttribute("userID") Long userID,
                            Model model){

        System.out.println(bookID);
        System.out.println(userID);

        Client client = users.findById(userID).get();
        Book book = books.findById(bookID).get();

        //if the cart has not been used yet, we create a cart for the client
        if(client.getCart() == null){
            System.out.println("no cart");
            client.setCart(new Cart());
        }
        if(!client.getCart().getBooks().contains(book)){
            client.addToCart(book);
        }
        else{
            System.out.println("Book is already in you're cart");
        }

        carts.save(client.getCart());
        users.save(client);

        model.addAttribute("Cart", client.getCart().getBooks());
        model.addAttribute("library", books.findAll());

        return "userHomepage";
    }

}
