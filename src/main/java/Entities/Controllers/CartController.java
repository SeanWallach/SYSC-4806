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
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private CartRepo carts;

    @Autowired
    private BookRepo books;

    @Autowired
    private ClientRepo users;

    private List<Book> recommendationList = new ArrayList<Book>();

    //----------------------------------------Add Book to Cart------------------------------------//
    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("bookID") Long bookID,
                            @RequestParam("userID") Long userID,
                            @RequestParam("bookQuantity") Long bookQuantity,
                            Model model){


        Client client = users.findById(userID).get();
        Book book = books.findById(bookID).get();

        //if the cart has not been used yet, we create a cart for the client
        if(client.getCart() == null){
            client.setCart(new Cart());
        }
        if(bookQuantity > 0 && bookQuantity <= book.getInventory()){
            System.out.println("bookQuantity = "+bookQuantity);
            client.addToCart(book, bookQuantity.intValue());
            book.setInventory(book.getInventory() - bookQuantity.intValue());

        }
        else{
            System.out.println("bookQuantity = "+bookQuantity);
            System.out.println("Invalid Quantity of Books");
        }

        carts.save(client.getCart());

        users.save(client);

        for (Book b: books.findAll()) {

            if (client.checkAuthorHistory(b)) {
                if (!recommendationList.contains(b)) {
                    recommendationList.add(b);
                }
            }

            if (!client.checkBookInHistory(b)) {
                if (!recommendationList.contains(b)) {
                    recommendationList.add(b);
                }
            }
        }

        model.addAttribute("userID",client.getId());
        model.addAttribute("CartBooks", client.getCart().getBooks());
        model.addAttribute("userCart", client.getCart());
        model.addAttribute("library", books.findAll());
        model.addAttribute("recommendationList", recommendationList);

        return "userHomepage";
    }
    //----------------------------------------Add Book to Cart------------------------------------//
    @GetMapping("/purchaseCart")
    public String purchase(@RequestParam("userID")Long userID,
                           Model model){

        Client client = users.findById(userID).get();
        Cart cart = client.getCart();

        System.out.println(client);
        System.out.println(cart);

        double sum = 0;
        int i = 0;
        for(Book b: cart.getBooks()){
            sum += b.getPrice() * cart.getQuantity(b);
            client.addBooktoHistory(b);
        }
        cart.setTotal(sum);

        cart.reset();

        carts.save(cart);
        users.save(client);

        model.addAttribute("Cart", cart);
        model.addAttribute("total", sum);
        return "purchaseReceipt";
    }
    //----------------------------------------Return to Shop------------------------------------//
    @RequestMapping("/back")
    public String back(@RequestParam("userID") Long userID, Model model){

        Client client = users.findById(userID).get();

        model.addAttribute("userID",client.getId());
        model.addAttribute("CartBooks", client.getCart().getBooks());
        model.addAttribute("userCart", client.getCart());
        model.addAttribute("library", books.findAll());
        return "userHomepage";
    }
}
