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
import java.util.Optional;

@Controller
public class CartController {
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
        client.addToCart(book);
        //users.save(client);

        for(Book b: client.getCart().getBooks()){
            System.out.println(b.getName());
        }
        model.addAttribute("Cart", client.getCart().getBooks());
        model.addAttribute("library", books.findAll());

        return "userHomepage";
    }

}
