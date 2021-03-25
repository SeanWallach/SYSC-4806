package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Book> books;

    private double total;



    public Cart(){ books = new ArrayList<Book>(); }

    public Cart(long id){
        this.id = id;
        books = new ArrayList<Book>();
    }

    public long getId() { return id;}

    public void setId(long id) {this.id = id;}

    public void removeBook(Book bookToRemove) {books.remove(bookToRemove);}

    public void addBook(Book bookToAdd) {books.add(bookToAdd);}

    public boolean checkForBook(Book book) {
        return books.contains(book);
    }

    public List<Book> getBooks(){
        return books;
    }

    public int getCartSize() {
        return books.size();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}