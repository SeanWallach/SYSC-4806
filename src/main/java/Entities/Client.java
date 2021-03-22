package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;

    @OneToOne
    private Cart cart;

    public Client(){}

    public Client(String username) {this.username = username;}


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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCart(Cart cart) { this.cart = cart;}

    public Cart getCart() {return cart;}
}
