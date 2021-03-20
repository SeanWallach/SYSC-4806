import Entities.Book;
import Entities.Cart;
import static org.junit.jupiter.api.Assertions.*;

public class TestCart {

    Cart cart;
    Book book;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.cart = new Cart(1);
        this.book = new Book("myBook", "author", "publisher", 1234, 13.0, 13, "test Cart");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @org.junit.jupiter.api.Test
    void testSetId() {
        cart.setId(1234);
        assertEquals(this.cart.getId(), 1234);
    }

    @org.junit.jupiter.api.Test
    void testAddBook() {
        cart.addBook(book);
        assertTrue(cart.checkForBook(book));
    }

    @org.junit.jupiter.api.Test
    void testRemoveBook() {
        cart.removeBook(book);
        assertEquals(cart.getCartSize(), 0);
    }

}
