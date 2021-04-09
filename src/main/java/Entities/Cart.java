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

    private int[] quantities;

    private double total;

    public Cart(){ books = new ArrayList<Book>();
        quantities = new int[10];
    }

    public Cart(long id){
        this.id = id;
        books = new ArrayList<Book>();
        quantities = new int[10];
    }

    public long getId() { return id;}

    public void setId(long id) {this.id = id;}

    public void removeBook(Book bookToRemove) {books.remove(bookToRemove);}

    public void addBook(Book bookToAdd, int quantity) {
        System.out.println("book: "+bookToAdd.getName());
        System.out.println("quantity: "+quantity);

        if(!books.contains(bookToAdd)){
            books.add(bookToAdd);
            for(int i = 0; i < quantities.length; i++){
                if(quantities[i] == 0){
                    quantities[i] = quantity;
                    System.out.println("new book order successful");
                    break;
                }
            }
        }
        else{
            int index = books.indexOf(bookToAdd);
            quantities[index] += quantity;
            System.out.println("repeat book order successful");
        }

    }

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

    public int getQuantity(Book book){
        int index = books.indexOf(book);
        return quantities[index];
    }


}