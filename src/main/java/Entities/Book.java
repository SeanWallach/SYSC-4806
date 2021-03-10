package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
public class Book {
    @Id
    private long ISBN;

    private String name;
    private String picture;
    private String description;
    private String author;
    private String publisher;
    private int inventory;
    private double price;
    //Picture attribute to be implemented


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Book(){}

    public Book(long ISBN, String name){
        this.ISBN = ISBN;
        this.name = name;
    }
    public Book(String name, String author, String publisher,
                long isbn, double price,
                int inventory, String description){
        this.name = name;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = isbn;
        this.price = price;
        this.inventory = inventory;
    }
}
