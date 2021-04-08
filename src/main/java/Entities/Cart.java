package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    private ArrayList<Integer> quantities;
    private HashMap<Book, Integer> bookMap;



    public Cart(){ books = new ArrayList<Book>();
        quantities = new ArrayList<Integer>();
        bookMap = new HashMap<Book, Integer>();
    }

    public Cart(long id){
        this.id = id;
        books = new ArrayList<Book>();
        quantities = new ArrayList<Integer>();
        bookMap = new HashMap<Book, Integer>();
    }

    public long getId() { return id;}

    public void setId(long id) {this.id = id;}

    public void removeBook(Book bookToRemove) {books.remove(bookToRemove);}

    public void addBook(Book bookToAdd, int quantity) {
        System.out.println("book: "+bookToAdd.getName());
        System.out.println("quantity: "+quantity);

        if(!books.contains(bookToAdd)){ books.add(bookToAdd); }

        /*if(!bookMap.containsKey(bookToAdd)){
            bookMap.put(bookToAdd, quantity);
        }
        else{
            int count = bookMap.get(bookToAdd) + quantity;
            bookMap.put(bookToAdd, count);
        }*/
        System.out.println("book Added successfully to hash");
    }

    public boolean checkForBook(Book book) {
        return books.contains(book);
    }

    public List<Book> getBooks(){
        return books;
    }

    public HashMap<Book, Integer> getBookMap(){return bookMap;}

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