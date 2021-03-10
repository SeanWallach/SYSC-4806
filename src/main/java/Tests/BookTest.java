package Tests;

import Entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    Book book;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.book = new Book("Harry Potter", "myAuthor", "myPublisher" ,12345, 9.99, 10, "myDescription" );

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @org.junit.jupiter.api.Test
    void testSetPrice(){
        book.setPrice(19.99);
        assertEquals(this.book.getPrice(), 19.99);
    }

    @org.junit.jupiter.api.Test
    void testSetISBN(){
        book.setISBN(11111);
        assertEquals(this.book.getISBN(), 11111);
    }

    @org.junit.jupiter.api.Test
    void testSetName(){
        book.setName("Hello");
        assertEquals(this.book.getName(), "Hello");
    }

    @org.junit.jupiter.api.Test
    void testSetDescription(){
        book.setDescription("Changed Description");
        assertEquals(this.book.getDescription(), "Changed Description");
    }

    @org.junit.jupiter.api.Test
    void testSetAuthor(){
        book.setAuthor("New Author");
        assertEquals(this.book.getAuthor(), "New Author");
    }

    @org.junit.jupiter.api.Test
    void testSetPublisher(){
        book.setPublisher("New Publisher");
        assertEquals(this.book.getPublisher(), "New Publisher");
    }

    @org.junit.jupiter.api.Test
    void testSetInventory(){
        book.setInventory(99);
        assertEquals(this.book.getInventory(), 99);
    }

}
