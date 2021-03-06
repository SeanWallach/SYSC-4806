package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;

    @OneToMany
    private List<Book> bookHistory = new ArrayList<Book>();

    @OneToOne
    private Cart cart;

    public Client(){}

    public Client(String username) {
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void addToCart(Book book, int quantity){
        cart.addBook(book, quantity);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCart(Cart cart) { this.cart = cart;}

    public Cart getCart() {return cart;}

    public void addBooktoHistory(Book book) {
        if(!bookHistory.contains(book)) {bookHistory.add(book);}
    }

    public boolean checkBookInHistory(Book book) {return bookHistory.contains(book);}

    public boolean checkAuthorHistory(Book book) {

        for (Book b: bookHistory){
            if (book.getAuthor() == b.getAuthor()) { return true;}

        }
        return false;
    }


}
